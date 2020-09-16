import React, {Component} from "react";
import {Button, ButtonGroup, Card, Table} from "react-bootstrap";
import axios from "axios";
import {faEdit, faTrash} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import MyToast from "./MyToast";

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

    deleteDataPerson = (idPerson) => {
        axios.delete("http://localhost:1212/api/person/data/"+idPerson)
            .then(response => {
                if (response.data != null){
                    this.setState({"show":true});
                    setTimeout(() => this.setState({"show":false}), 3000);
                    this.setState({
                        dataPerson: this.state.dataPerson.filter(person => person.idPerson !== idPerson)
                    });
                } else {
                    this.setState({"show":false});
                }
            })
    }

    render() {
        return(
            <div>
                <div style={{"display":this.state.show ? "block":"none"}}>
                    <MyToast children={{show:this.state.show, message: "Data berhasil dihapus."}}/>
                </div>
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
                                    <tr key={person.idPerson}>
                                        <td>{person.nik}</td>
                                        <td>{person.nama}</td>
                                        <td>{person.alamat}</td>
                                        <td>{person.tanggalLahir}</td>
                                        <td>{person.tempatLahir}</td>
                                        <td>
                                            <ButtonGroup>
                                                <Button size="sm" variant="outline-primary"><FontAwesomeIcon icon={faEdit} /></Button>{' '}
                                                <Button size="sm" variant="outline-danger" onClick={this.deleteDataPerson.bind (this, person.idPerson)}><FontAwesomeIcon icon={faTrash} /></Button>
                                            </ButtonGroup>
                                        </td>
                                    </tr>
                                ))
                            }
                            </tbody>
                        </Table>
                    </Card.Body>
                </Card>
            </div>
        );
    }
}