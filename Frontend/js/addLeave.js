let result = JSON.stringify({});

function handleSubmit(event) {
    event.preventDefault();

    const data = new FormData(event.target);

    const empId = data.get('emp-list');
    const fromDate = data.get('fromDate');
    const toDate = data.get('toDate');
    const description = data.get('description');

    result = JSON.stringify({empId, fromDate, toDate, description});

    console.log(result);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/v1/jobleave");

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = () => {
        console.log(xhr.responseText);
    };

    xhr.send(result);

    // Swal.fire({
    //     title: 'Success!',
    //     text: 'Successfully Submitted',
    //     icon: 'success',
    //     confirmButtonText: 'Done'
    // }).then(() => {
    location.replace("dashboard_main.html");
    // })
}

const form = document.querySelector('#leave-add-form');
form.addEventListener('submit', handleSubmit);

document.querySelector('#leave-add-form').innerHTML = `<div class="loader"></div>`;

const url = 'http://localhost:8080/api/v1/employee';

fetch(url)
    .then(response => response.json())
    .then((employees) => {

        document.querySelector('#leave-add-form').innerHTML = `
            <div class="row">
                            <div class="col-half">
                                <h4>Employee Name</h4>
                                <select name="emp-list" id="emp-list" class="input-group drop-down">

                                </select>
                            </div>
                            <div class="col-half">

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-half">
                                <h4>From Date</h4>
                                <div class="input-group">
                                    <input type="date" name="fromDate" id="fromDate" placeholder="From Date"/>
                                </div>
                            </div>
                            <div class="col-half">
                                <h4>To date</h4>
                                <div class="input-group">
                                    <input type="date" name="toDate" id="toDate"  placeholder="To date"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <h4>Description</h4>
                            <div class="input-group">
                                <input type="text" name="description" id="description" placeholder="Description"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <button type="submit" class="submit-btn">Submit</button>
                            </div>
                        </div>
       
        `;


        let placeholder = document.querySelector("#emp-list");

        let out = "";

        for (let employee of employees) {
            out += `
            <option value=${employee.empId}>${employee.name}</option>
            `
            placeholder.innerHTML = out;
        }
    })