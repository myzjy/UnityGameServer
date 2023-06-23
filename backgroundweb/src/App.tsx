import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import './App.css';
import Hello from "./hello";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/" Component={Hello} />

                    <Route path="/rout" Component={Hello} />
                </Routes>


            </BrowserRouter>
            {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <p>
          <code color="#fffff">public</code>
        </p>
      </header> */}
        </div>
    );
}

export default App;
