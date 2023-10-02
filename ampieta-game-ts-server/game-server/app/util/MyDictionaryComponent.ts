import {IComponent} from "pinus/lib/interfaces/IComponent";
import {Application} from "pinus/lib/application";
import * as Loader from "pinus-loader";
import {LoaderPathType} from "pinus-loader";
import {listEs6ClassMethods} from "pinus-rpc";
import * as path from "path";
import * as crypto from 'crypto';
import {getLogger} from "pinus-logger";
import * as Constants from "pinus/lib/util/constants";
import * as fs from "fs";

let logger = getLogger('pinus', path.basename(__filename));

export interface MyDictionaryComponentOptions {
    dict?: string;
    // 不自动按照路由生成router,仅使用 config/dictionary 内的路由.
    // 这样路由的 id 就可以通过dictionary的顺序来控制了,方便proto变更不影响原顺序id (为也热更新考虑)
    // 另外这样也少一次load handler
    ignoreAutoRouter?: boolean;
}

function canResolve(path: string) {
    try {
        require.resolve(path);
    } catch (err) {
        return false;
    }
    return true;
}

export class MyDictionaryComponent implements IComponent {
    app: Application;
    dict: { [key: string]: number } = {};
    abbrs: { [key: string]: string } = {};
    userDicPath: string;
    version = '';
    name = '__myDictionary__';
    ignoreAutoRouter: boolean;

    constructor(app: Application) {
        this.app = app;

        // Set user dictionary
        let p = path.join(app.getBase(), '/config/dictionary');

        this.ignoreAutoRouter = false;

        if (canResolve(p)) {
            this.userDicPath = p;
        }
    }


    start(cb: () => void) {
        let servers = this.app.get('servers');
        let routes = [];
        if (!this.ignoreAutoRouter) {
            // Load all the handler files
            for (let serverType in servers) {
                let pS = path.join(this.app.getBase(), '/app/servers/', serverType, Constants.DIR.HANDLER);
                let p = fs.existsSync(pS)?pS:null
                if (!p) {
                    continue;
                }
                let handlers = Loader.load(p, this.app, false, false, LoaderPathType.PINUS_HANDLER);

                for (let name in handlers) {
                    let handler = handlers[name];

                    let proto = listEs6ClassMethods(handler);
                    for (let key of proto) {
                        routes.push(serverType + '.' + name + '.' + key);
                    }
                }
            }
        }


        // 对路由进行排序，以确保所有服务器中的所有路由器abbr都是相同的
        routes.sort();

        console.warn('启动所有服务器后，使用路由字典 :\n', routes.join('\n'));

        let abbr;
        let i;
        for (i = 0; i < routes.length; i++) {
            abbr = i + 1;
            this.abbrs[abbr] = routes[i];
            this.dict[routes[i]] = abbr;
            logger.log("index" + i + ",route:" + routes[i] + ",");
        }

        // 载入用户字典
        if (!!this.userDicPath) {
            let userDic = require(this.userDicPath);

            abbr = routes.length + 1;
            for (i = 0; i < userDic.length; i++) {
                let route = userDic[i];

                this.abbrs[abbr] = route;
                this.dict[route] = abbr;
                abbr++;
            }
        }

        this.version = crypto.createHash('md5').update(JSON.stringify(this.dict)).digest('base64');
        process.nextTick(cb);
    }

    getDict() {
        return this.dict;
    }

    getAbbrs() {
        return this.abbrs;
    }

    getVersion() {
        return this.version;
    }

}
