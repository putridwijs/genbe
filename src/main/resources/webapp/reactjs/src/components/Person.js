import React, {Component} from "react";
import {Card, Form, Button, Col} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSave, faPlusCircle, faUndo} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import MyToast from "./MyToast";

export default class Person extends Component {
    constructor(props){
        super(props);
        this.state= this.initialState;
        this.state.show = false;
        this.dataChange= this.dataChange.bind(this);
        this.submitData= this.submitData.bind(this);
    }
    initialState = {
        nik:'', nama:'', alamat:'', tanggalLahir:'', tempatLahir:''
    }
    resetData = () => {
        this.setState(() => this.initialState);
    }
    submitData = event => {
        event.preventDefault();
        const dataPerson = {
            nik: this.state.nik,
            nama: this.state.nama,
            alamat: this.state.alamat,
            tanggalLahir: this.state.tanggalLahir,
            tempatLahir: this.state.tempatLahir
        };

        axios.post("http://localhost:1212/api/person", dataPerson)
            .then(response =>{
               if (response.data != null){
                   this.setState({"show":true});
                   setTimeout(() => this.setState({"show":false}), 3000);
               } else {
                   this.setState({"show":false});
               }
            });

        this.setState(this.initialState);
    }
    dataChange = event => {
        this.setState({
           [event.target.name]:event.target.value
        });
    }
    render() {
        const {nik, nama, alamat, tanggalLahir, tempatLahir} = this.state;
        return(
            <div>
                <div style={{"display":this.state.show ? "block":"none"}}>
                    <MyToast children={{show:this.state.show, message: "Data berhasil masuk."}}/>
                </div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><FontAwesomeIcon icon={faPlusCircle} /> Tambah Data Person</Card.Header>
                    <Form onReset={this.resetData} onSubmit={this.submitData} id="person">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formNik">
                                    <Form.Label>NIK</Form.Label>
                                    <Form.Control type="text" name="nik" value={nik} autoComplete="off"
                                                  onChange={this.dataChange} required placeholder="NIK" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formTanggal">
                                    <Form.Label>Tanggal Lahir</Form.Label>
                                    <Form.Control type="date" name="tanggalLahir" value={tanggalLahir} autoComplete="off"
                                                  onChange={this.dataChange} required placeholder="Tanggal Lahir" />
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group as={Col} controlId="formNama">
                                    <Form.Label>Nama</Form.Label>
                                    <Form.Control type="text" name="nama" value={nama} autoComplete="off"
                                                  onChange={this.dataChange} required placeholder="Nama" />
                                </Form.Group>

                                <Form.Group as={Col} controlId="formTempat">
                                    <Form.Label>Tempat Lahir</Form.Label>
                                    <Form.Control type="text" name="tempatLahir" value={tempatLahir} autoComplete="off"
                                                  onChange={this.dataChange} required placeholder="Tempat Lahir" />
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group as={Col} controlId="formAlamat">
                                    <Form.Label>Alamat</Form.Label>
                                    <Form.Control type="text" name="alamat" value={alamat} autoComplete="off"
                                                  onChange={this.dataChange} required placeholder="Alamat" />
                                </Form.Group>
                                <Form.Group as={Col}>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign": "right"}}>
                            <Button size="sm" variant="success" type="submit">
                                <FontAwesomeIcon icon={faSave} /> Submit
                            </Button>{' '}
                            <Button size="sm" variant="info" type="reset">
                                <FontAwesomeIcon icon={faUndo} /> Reset
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
}