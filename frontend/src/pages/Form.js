import { useEffect, useState } from 'react';
import axios from 'axios';

export default function Form() {
    const [name, setName] = useState('')
    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')

    useEffect(() => {
        document.title = `Test Adria | Add User`
    }, [])

    const handleSubmit = async(e) => {
        e.preventDefault()

        const selectedValue = document.getElementById("selectVal")
        const data = {
            'selected': selectedValue.value,
            'name': name,
            'username': username,
            'email': email,
        }

        //Add Axios to API
        const response = axios.put("http://localhost:8082")
    }



    return (
        <div className='formDiv'>
            <h3>Users - Add User</h3>
            <form>
                <label className="form-label">Visibility</label>
                <div className="mb-3" style={{display: "flex"}}>
                    <select className="form-control visibility" id="selectVal">
                        <option value=''>Visibility</option>
                        <option value="public">Pubic</option>
                        <option value="meOnly">Me only</option>
                    </select>
                </div>

                <div className="mb-3">
                    <label className="form-label">Name</label>
                    <input type="text" className="form-control" onChange={(e) => {setName(e.target.value)}}/>
                </div>

                <div className="mb-3">
                    <label className="form-label">Username</label>
                    <input type="text" className="form-control" onChange={(e) => {setUsername(e.target.value)}}/>
                </div>

                <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input type="text" className="form-control" onChange={(e) => {setEmail(e.target.value)}}/>
                </div>

                <button type="submit" className="btn btn-primary" onClick={handleSubmit}>Submit</button>
            </form>
        </div>
    )
}