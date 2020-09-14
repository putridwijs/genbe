import React from "react";
import {Jumbotron} from "react-bootstrap";

class Dashboard extends React.Component {
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

export default Dashboard;