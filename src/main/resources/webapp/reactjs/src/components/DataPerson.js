import React, {Component} from "react";
import {Button, ButtonGroup, Card, Table} from "react-bootstrap";
import axios from "axios";
import {faEdit, faTrash} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

export default class DataPerson extends Component {
    constructor(props) {
        super(props);
        this.state = {
          dataPerson : []
        };
    }
    componentDidMount() {
        this.findAllDataPerson();
    }
    findAllDataPerson(){
        axios.get("http://localhost:1212/api/person")
            .then(response => response.data)
            .then((data) => {
                this.setState({dataPerson: data});
            });
    }

    render() {
        return(
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>Data Person</Card.Header>
                <Card.Body>
                    <Table bordered hover striped variant="dark">
                        <thead>
                        <tr>
                            <th>NIK</th>
                            <th>Nama</th>
                            <th>Alamat</th>
                            <th>Tanggal Lahir</th>
                            <th>Tempat Lahir</th>
                            <th>Edit</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.dataPerson.length === 0 ?
                            <tr align={"center"}>
                                <td colSpan={"5"}> Data Tersedia</td>
                            </tr> :
                            this.state.dataPerson.map((person) => (
                                <tr key={person.id}>
                                    <td>{person.nik}</td>
                                    <td>{person.nama}</td>
                                    <td>{person.alamat}</td>
                                    <td>{person.tanggalLahir}</td>
                                    <td>{person.tempatLahir}</td>
                                    <td>
                                        <ButtonGroup>
                                            <Button size="sm" variant="outline-primary"><FontAwesomeIcon icon={faEdit} /></Button>{' '}
                                            <Button size="sm" variant="outline-danger"><FontAwesomeIcon icon={faTrash} /></Button>
                                        </ButtonGroup>
                                    </td>
                                </tr>
                            ))
                        }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}