import './App.css'

import { useEffect, useState } from "react";

function App() {

    // State to store fetched countries from backend
    const [countries, setCountries] = useState([]);
    const [search, setSearch] = useState("");
    const [selected, setSelected] = useState(null);

    useEffect(() => {
        fetch("http://localhost:8080/api/countries")
            .then(res => res.json())
            .then(data => setCountries(data));
    }, []);

    const filtered = countries.filter(c =>
        c.name.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <div>

            <h2>Countries</h2>

            {/* Search input */}
            <input
                placeholder="Search country..."
                onChange={(e) => setSearch(e.target.value)}
            />

            <table border="1">
                <thead>
                <tr>
                    <th>Flag</th>
                    <th>Name</th>
                    <th>Capital</th>
                    <th>Region</th>
                    <th>Population</th>
                </tr>
                </thead>

                <tbody>
                {filtered.map((c, index) => (
                    <tr key={index} onClick={() => setSelected(c)}>
                        <td>
                            <img src={c.flag} width="40"/>
                        </td>
                        <td>{c.name}</td>
                        <td>{c.capital}</td>
                        <td>{c.region}</td>
                        <td>{c.population}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            {/* Modal popup for selected country */}
            {selected && (
                <div style={{
                    position:"fixed",
                    top:"30%",
                    left:"40%",
                    background:"#fff",
                    padding:"20px",
                    border:"1px solid black"
                }}>

                    <h3>{selected.name}</h3>
                    <p>Capital: {selected.capital}</p>
                    <p>Region: {selected.region}</p>
                    <p>Population: {selected.population}</p>

                    <button onClick={() => setSelected(null)}>Close</button>

                </div>
            )}

        </div>
    );
}

export default App;