import React, {Component} from "react";
import {Jumbotron} from "react-bootstrap";

export default class Dashboard extends Component {
    render() {
        return(
            <Jumbotron className={"bg-dark text-white"}>
                <h1>Selamat Datang</h1>
                <p>
                    Prodemy PRO SIGMAKA MANDIRI
                </p>
            </Jumbotron>
        );
    }
}