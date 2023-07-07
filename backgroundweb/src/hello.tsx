import { render } from "@testing-library/react";
import { clear } from "console";
import { type } from "os";
import React, { Component } from "react";
interface IHello {
    message?: string;
}


const Hello: React.FunctionComponent<IHello> = (res) => {
    let i = 1;
    const num = (res: IHello) => {
        console.log("点击了")
        console.log(res.message)
        res.message = "点击" + (i++)

    }

    return <div>
        hello,{res.message}
        <button onClick={() => num(res)}>{res.message}</button>

    </div>
}
Hello.defaultProps = {
    message: "无上？？"
}

export default Hello;
