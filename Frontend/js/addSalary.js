let result = JSON.stringify({});

function handleSubmit(event) {
    event.preventDefault();

    const data = new FormData(event.target);

    const empId = data.get('empId');
    const salMonth = data.get('salMonth');
    const totalDays = data.get('totalDays');
    const basicSalary = data.get('basicSalary');
    const bonus = data.get('bonus');

    result = JSON.stringify({empId, salMonth, totalDays, basicSalary, bonus});

    console.log(result);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/v1/salary");

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = () => {
        console.log(xhr.responseText);
    };

    xhr.send(result);

    location.replace("dashboard_main.html");

    // Swal.fire({
    //     title: 'Success!',
    //     text: 'Successfully Submitted',
    //     icon: 'success',
    //     confirmButtonText: 'Done'
    // }).then(() => {
    //
    // })
}


const form = document.querySelector('#salary-add-form');
form.addEventListener('submit', handleSubmit);


const url = 'http://localhost:8080/api/v1/employee';

document.querySelector('#salary-add-form').innerHTML = `<div class="loader"></div>`;

fetch(url)
    .then(response => response.json())
    .then((employees) => {


        document.querySelector('#salary-add-form').innerHTML = `
    <div class="row">
                            <div class="col-half">
                                <h4>Employee Name</h4>
                                <select name="empId" id="empId" class="input-group drop-down">

                                </select>
                            </div>
                            <div class="col-half">
                                <h4>Month</h4>
                                <select name="salMonth" id="salMonth" class="input-group drop-down">
                                    <option value="January">January</option>
                                    <option value="February">February</option>
                                    <option value="March">March</option>
                                    <option value="April">April</option>
                                    <option value="May">May</option>
                                    <option value="June">June</option>
                                    <option value="July">July</option>
                                    <option value="August">August</option>
                                    <option value="September">September</option>
                                    <option value="October">October</option>
                                    <option value="November">November</option>
                                    <option value="December">December</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-half">
                                <h4>Total Days</h4>
                                <div class="input-group">
                                    <input type="text" name="totalDays" id="totalDays" placeholder="Total Days"/>
                                </div>
                            </div>
                            <div class="col-half">
                                <h4>Basic Salary</h4>
                                <div class="input-group">
                                    <input type="text" name="basicSalary" id="basicSalary" placeholder="Basic Salary"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-half">
                                <h4>Bonus</h4>
                                <div class="input-group">
                                    <input name="bonus" id="bonus" type="text" placeholder="Bonus"/>
                                </div>
                            </div>
                            <div class="col-half">

                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <button type="submit" class="submit-btn">Submit</button>
                            </div>
                        </div>
`;

        let placeholder = document.querySelector("#empId");
        let out = "";

        for (let employee of employees) {
            out += `
            <option value=${employee.empId}>${employee.name}</option>
            `
            placeholder.innerHTML = out;
        }
    })

