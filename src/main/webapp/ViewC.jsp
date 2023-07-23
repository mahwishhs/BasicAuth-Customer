<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Data</title>
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Poppins" />
    <style>
        body {
            font-family: "Poppins", sans-serif;
            background-color: #1D1D41;
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

    </style>
</head>
<body>
<div class="bannerTop">
    <span class="bannerHeading">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp>&nbspADD CUSTOMER</span>
</div>
<br><br><br><br><br><br>

<div id="tableContainer"></div>

<script>
    // Cache the table container
    const tableContainer = document.getElementById('tableContainer');

    // Function to fetch customer data
    function fetchData() {
        fetch('api/customers')
            .then(response => response.json())
            .then(data => {
                // Call createTable to display the data in a table
                createTable(data);
            })
            .catch(error => {
                console.error("Error fetching data: ", error);
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

    // Call fetchData to fetch the data when the page loads
    fetchData();
</script>
</body>
</html>
