import { useEffect, useState } from 'react';
import axios from 'axios';

export default function UsersView() {
    const [data, setData] = useState([])

    useEffect(() => {
        axios.get("https://localhost:8081/beneficiaries").then(res => {
        setData(res.data)
        }).catch(err => {
        console.error(err)
        })
    })

    useEffect(() => {
        document.title = `Test Adria | Data View`
    }, [])

    return (
        <div className="viewer">
            <h3>Users - View</h3>
            <table class="table table-hover">
                <thead>
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Username</th>
                    <th scope="col">Email</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        data.map(data => (
                            <tr>
                                <th scope="row">{data.id}</th>
                                <td>{data.name}</td>
                                <td>{data.username}</td>
                                <td>{data.email}</td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}