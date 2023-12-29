import { pinus } from 'pinus';
import { preload } from './preload';
import { MyHybridConnector } from "./app/util/myHrbridconnector";
import * as path from 'path';

/**
 *  替换全局Promise
 *  自动解析sourcemap
 *  捕获全局错误
 */
preload();

/**
 * Init app for client.
 */
let app = pinus.createApp();
app.set('name', 'tsServer');

// app configuration
app.configure('production|development', 'connector', function() {
  // app.set('dictionaryConfig', {dict: path.join(app.getBase(), '/config/dictionary'), ignoreAutoRouter: false});
  app.set('connectorConfig',
    {
      connector: MyHybridConnector,
      heartbeat: 3,
      useDict: true,
      forwardMsg: false,
      useProtobuf: false
    });
});

// start app
app.start();
