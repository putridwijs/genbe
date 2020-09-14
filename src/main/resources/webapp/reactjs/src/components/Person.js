import React, {Component} from "react";
import {Card, Form, Button, Col} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSave, faPlusCircle} from "@fortawesome/free-solid-svg-icons";

export default class Person extends Component {
    constructor(props){
        super(props);
        this.state= {nik:'', nama:'', alamat:'', tanggalLahir:'', tempatLahir:''}
        this.dataChange= this.dataChange.bind(this);
        this.submitData= this.submitData.bind(this);
    }
    submitData(event){
        alert("success");
        event.preventDefault();
    }
    dataChange(event){
        this.setState({
           [event.target.name]:event.target.value
        });
    }
    render() {
        return(
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header><FontAwesomeIcon icon={faPlusCircle} /> Tambah Data Person</Card.Header>
                <Form onSubmit={this.submitData} id="person">
                    <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formNik">
                                    <Form.Label>NIK</Form.Label>
                                    <Form.Control type="text" name="nik" value={this.state.nik}
                                                  onChange={this.dataChange} required placeholder="NIK" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formTanggal">
                                    <Form.Label>Tanggal Lahir</Form.Label>
                                    <Form.Control type="date" name="tanggalLahir" value={this.state.tanggalLahir}
                                                  onChange={this.dataChange} required placeholder="Tanggal Lahir" />
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group as={Col} controlId="formNama">
                                    <Form.Label>Nama</Form.Label>
                                    <Form.Control type="text" name="nama" value={this.state.nama}
                                                  onChange={this.dataChange} required placeholder="Nama" />
                                </Form.Group>

                                <Form.Group as={Col} controlId="formTempat">
                                    <Form.Label>Tempat Lahir</Form.Label>
                                    <Form.Control type="text" name="tempatLahir" value={this.state.tempatLahir}
                                                  onChange={this.dataChange} required placeholder="Tempat Lahir" />
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group as={Col} controlId="formAlamat">
                                    <Form.Label>Alamat</Form.Label>
                                    <Form.Control type="text" name="alamat" value={this.state.alamat}
                                                  onChange={this.dataChange} required placeholder="Alamat" />
                                </Form.Group>
                                <Form.Group as={Col}>
                                </Form.Group>
                            </Form.Row>
                    </Card.Body>
                    <Card.Footer style={{"textAlign": "right"}}>
                        <Button size="sm" variant="success" type="submit">
                            <FontAwesomeIcon icon={faSave} /> Submit
                        </Button>
                    </Card.Footer>
                </Form>
            </Card>
        );
    }
}