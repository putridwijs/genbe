import React from 'react';
import './App.css';
import {Container, Row, Col} from "react-bootstrap";
import NavigationBar from "./components/NavigationBar";
import Dashboard from "./components/Dashboard";
import Footer from "./components/Footer";
import DataPerson from "./components/DataPerson";
import Person from "./components/Person";
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";


function App() {
    const marginTop = {
        marginTop:"20px"
    };
  return (
    <Router>
        <NavigationBar/>
        <Container>
            <Row>
                <Col lg={12} style={marginTop}>
                    <Switch>
                        <Route path="/" exact component={Dashboard}/>
                        <Route path="/data" exact component={DataPerson}/>
                        <Route path="/tambah" exact component={Person}/>
                    </Switch>
                </Col>
            </Row>
        </Container>
        <Footer/>
    </Router>
  );
}

export default App;
