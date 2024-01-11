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
        console.log(res.message)
        res.message = "点击" + (i++)
    }

    const showText = () => {
        console.log(1)
        return "hello" + res.message
    }


    return <div>
        <text> hello,{showText()}</text>
        <button onClick={() => { console.log("点击") }}>点击其天涯</button>
        <button onClick={() => num(res)}>{res.message}</button>

    </div>
}
Hello.defaultProps = {
    message: "无上？？"
}

export default Hello;
