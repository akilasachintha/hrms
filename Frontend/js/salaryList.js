class SalaryList {

    url = 'http://localhost:8080/api/v1/salary';

    fetchSalaryDetails = () => {

        document.querySelector('#salary-list').innerHTML = `<div class="loader"></div>`;

        fetch(this.url)
            .then(response => response.json())
            .then((salaries) => {
                let placeholder = document.querySelector("#salary-list");
                let out = "";

                for (let {salId, name, salMonth, totalDays, basicSalary, bonus} of salaries) {
                    out += `
            <tr>
                <td>${salId}</td>
                                <td>
                                    <a class="text-heading font-semibold" href="#">
                                        ${name}
                                    </a>
                                </td>
                                <td>
                                    ${salMonth}
                                </td>
                                <td>
                                    ${totalDays}
                                </td>
                                <td>
                                    ${basicSalary}
                                </td>
                                <td>
                                    ${bonus}
                                </td>
                                <td>
                                    ${basicSalary + bonus}
                                </td>
                                <td class="text-end" id=${salId}>
                                    <button class="btn btn-sm btn-neutral edit" id="sal-edit-btn-${salId}">Edit</a>
                                    <button type="button" class="btn btn-sm btn-square btn-neutral text-danger-hover bi bi-trash delete">
                                     
                                    </button>
                                </td>
                            </tr>
            `

                    placeholder.innerHTML = out;
                }
            })
    }


    editSalaryDetails = () => {
        document.getElementById('salary-list')?.addEventListener('click', (e) => {
            if (e.target.classList.contains("edit")) {
                let btn = e.target.id;

                console.log(btn);

                fetch(this.url + "/" + e.target.parentElement.id)
                    .then(response => response.json())
                    .then((salary) => {
                        console.log(salary);
                        let modal = document.getElementById("myModal");
                        let span = document.getElementsByClassName("close")[0];

                        document.getElementById(btn).addEventListener('click', () => {
                            modal.style.display = "block";

                            modal.innerHTML = `

            <div class="modal-content">
                <span class="close">&times;</span>
                <div class="container">
                    <form method="post" id="salary-add-form">
                        
                        <div class="row">
                            <div class="col-half">
                                <h4>Total Days</h4>
                                <div class="input-group">
                                    <input type="text" name="totalDays" id="totalDays" placeholder="Total Days" value=${salary.totalDays}>
                                </div>
                            </div>
                            <div class="col-half">
                                <h4>Basic Salary</h4>
                                <div class="input-group">
                                    <input type="text" name="basicSalary" id="basicSalary" placeholder="Basic Salary" value=${salary.basicSalary}>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-half">
                                <h4>Bonus</h4>
                                <div class="input-group">
                                    <input name="bonus" id="bonus" type="text" placeholder="Bonus" value=${salary.bonus}>
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="input-group">
                                <button type="submit" class="submit-btn">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
                </div>
            `;
                            let form = document.querySelector('#salary-add-form');
                            form.addEventListener('submit', handleSubmit);
                        })

                        let result = JSON.stringify({});

                        function handleSubmit(event) {
                            event.preventDefault();

                            const data = new FormData(event.target);

                            const salMonth = salary.salMonth;
                            const totalDays = data.get('totalDays');
                            const basicSalary = data.get('basicSalary');
                            const bonus = data.get('bonus');
                            const empId = salary.empId;

                            result = JSON.stringify({empId, salMonth, totalDays, basicSalary, bonus});

                            console.log(result);

                            let xhr = new XMLHttpRequest();
                            xhr.open("PUT", "http://localhost:8080/api/v1/salary/" + e.target.parentElement.id);

                            xhr.setRequestHeader("Accept", "application/json");
                            xhr.setRequestHeader("Content-Type", "application/json");

                            xhr.onload = () => {
                                console.log(xhr.responseText);
                            };

                            xhr.send(result);

                            window.location.reload();
                        }

                        span.onclick = function () {
                            modal.style.display = "none";
                        }

                        window.onclick = function (event) {
                            if (event.target === modal) {
                                modal.style.display = "none";
                            }
                        }
                    })
            }
        })
    }

    deleteSalary = () => {
        document.getElementById('salary-list')?.addEventListener('click', (e) => {

            if (e.target.classList.contains("delete")) {

                if (confirm("Are you Sure to Delete!")) {
                    let id = e.target.parentElement.id;
                    fetch(url + "/" + id.toString(), {method: 'DELETE'})
                        .then(response => {
                            console.log(response.json());

                            location.reload();
                        })
                        .catch(error => {
                            console.error('There was an error!', error);
                        });
                }
            }
        });
    }
}



const salary = new SalaryList();

salary.fetchSalaryDetails();
salary.editSalaryDetails();
salary.deleteSalary();






