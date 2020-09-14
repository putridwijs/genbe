import React, {Component} from "react";
import {Card, Table} from "react-bootstrap";

export default class DataPerson extends Component {
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
                        </tr>
                        </thead>
                        <tbody>
                        <tr align={"center"}>
                            <td colSpan={"5"}>Tidak Ada Data</td>
                        </tr>
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}