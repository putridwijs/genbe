import React from "react";
import {Navbar, Nav, Form, FormControl, Button} from "react-bootstrap";
import {Link} from "react-router-dom";

class NavigationBar extends React.Component {
    render() {
        return(
            <Navbar className="bg-navy" bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                    Prodemy
                </Link>
                <Nav className="mr-auto">
                    <Link to={"data"} className="nav-link">Data Person</Link>
                    <Link to={"tambah"} className="nav-link">Tambah Data Person</Link>
                </Nav>
                <Form inline>
                    <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                    <Button variant="outline-info">Search</Button>
                </Form>
            </Navbar>
        );
    }
}

export default NavigationBar;