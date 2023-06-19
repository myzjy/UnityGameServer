import React from "react";

interface IHello {
    message?: string;
}

const Hello: React.FunctionComponent<IHello> = (res) => {
    let i = 1;
    const num = (res: IHello) => {
        console.log("点击了")
        res.message="点击" + (i++)
    }
    return <div>
        hello,{res.message}
        <button onClick={() => num(res)}>点击</button>

    </div>
}
Hello.defaultProps = {
    message: "无上？？"
}

export default Hello;