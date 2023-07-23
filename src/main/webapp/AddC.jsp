<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Customers</title>
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Poppins" />
    <style>
        body {
            font-family: "Poppins", sans-serif;
            background-color: #1D1D41;
        }
        .bannerTop{
            width: 700px;
            height: 60px;
            background-color: #6f6ff6;
            float: right;
            position: relative;
            top:40px;
            border-radius:100px 0px 0px 2px;
        }

        .bannerHeading{
            color: white;
            font-family: 'Poppins';
            font-size: 14pt;
            letter-spacing: 1pt;
            font-weight: bold;
            position: absolute;
            top: 25%;
        }

        form {
            background-color: #6f6ff6;
            font-family: "Poppins", sans-serif;
            display: flex;
            flex-direction: column;
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border-style: none;
            border-radius: 5px;
        }

        form label {
            color: black;
            margin-bottom: 8px;
            font-weight: bold;
        }

        form input[type="text"] {
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
        }

        form button {
            background-color: #e0d735;
            color: #000000;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-family: "Poppins", sans-serif;
        }

        form button:hover {
            background-color: #e0d735;
        }

        table {
            background-color: #6f6ff6;
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #e0d735;
        }
    </style>
</head>
<body>
<div class="bannerTop">
    <span class="bannerHeading">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp>&nbspADD CUSTOMER</span>
</div>
<br><br><br><br><br><br>


<form id="addForm">
    <label for="input-id">ID:</label>
    <input type="text" id="input-id" placeholder="Enter ID" required>
    <label for="input-name">Name:</label>
    <input type="text" id="input-name" placeholder="Enter Name" required>
    <label for="input-email">Email:</label>
    <input type="text" id="input-email" placeholder="Enter Email" required>
    <label for="input-address">Address:</label>
    <input type="text" id="input-address" placeholder="Enter Address" required>
    <label for="input-phone">Phone:</label>
    <input type="text" id="input-phone" placeholder="Enter Phone" required>
    <button type="submit">Add Customer</button>
</form>

<!-- Container for the table -->
<div id="tableContainer"></div>

<script>
    // Cache the form and table container
    const addForm = document.getElementById('addForm');
    const tableContainer = document.getElementById('tableContainer');

    // Function to add a new customer
    function addCustomer(event) {
        event.preventDefault();

        const id = document.getElementById('input-id').value;
        const name = document.getElementById('input-name').value;
        const email = document.getElementById('input-email').value;
        const address = document.getElementById('input-address').value;
        const phone = document.getElementById('input-phone').value;

        const newCustomer = {
            id: id,
            name: name,
            email: email,
            address: address,
            phone: phone
        };

        fetch('api/customers', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newCustomer)
        })
            .then(response => response.text())
            .then(message => {
                alert(message);
                fetchData();
                addForm.reset(); // Reset the form fields after successful addition
            })
            .catch(error => {
                console.error('Error adding customer:', error);
            });
    }

    // Function to fetch customer data
    function fetchData() {
        fetch('api/customers')
            .then(response => response.json())
            .then(data => {
                // Call createTable to display the data in a table
                createTable(data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }

    // Function to create the table
    function createTable(data) {
        // Create the table element
        const table = document.createElement('table');
        table.setAttribute('border', '1');

        // Create the table header row
        const thead = document.createElement('thead');
        const headerRow = document.createElement('tr');

        // Get the column names from the first object in the array
        const columns = Object.keys(data[0]);

        // Create table headers and add them to the header row
        columns.forEach(column => {
            const th = document.createElement('th');
            th.textContent = column;
            headerRow.appendChild(th);
        });

        // Add the header row to the table header
        thead.appendChild(headerRow);

        // Create the table body and rows
        const tbody = document.createElement('tbody');
        data.forEach(customer => {
            const row = document.createElement('tr');

            // Create table cells and add them to the row
            columns.forEach(column => {
                const cell = document.createElement('td');
                cell.textContent = customer[column];
                row.appendChild(cell);
            });

            // Add the row to the table body
            tbody.appendChild(row);
        });

        // Add the table header and body to the table
        table.appendChild(thead);
        table.appendChild(tbody);

        // Clear any existing table content and add the new table to the container
        tableContainer.innerHTML = '';
        tableContainer.appendChild(table);
    }

    // Add event listener to the form submit
    addForm.addEventListener('submit', addCustomer);

    // Fetch the data and create the table when the page loads
    fetchData();
</script>
</body>
</html>
