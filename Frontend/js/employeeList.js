const url = 'http://localhost:8080/api/v1/employee';


document.querySelector('#employee-list').innerHTML = `<div class="loader"></div>`;

fetch(url)
    .then(response => response.json())
    .then((employees) => {
        let placeholder = document.querySelector("#employee-list");
        let out = "";

        for (let employee of employees) {
            out += `
            <tr class="employee-data">
                <td>${employee.empId}</td>
                <td>
                    <a class="text-heading font-semibold" href="#">
                        ${employee.name}
                    </a>
                </td>
                <td>
                    ${employee.dob}
                </td>
                <td>
                    ${employee.email}
                </td>
                <td>
                    ${employee.contactNo}
                </td>
                <td>
                    ${employee.country}
                </td>
                <td>
                    Software Engineer
                </td>
                <td class="text-end" id=${employee.empId}>
                    <button class="btn btn-sm btn-neutral edit" id="edit-btn-${employee.empId}">Edit</button>
                    <button type="button" class="btn btn-sm btn-square btn-neutral text-danger-hover delete bi bi-trash delete">
                    </button>
                 </td>
            </tr>
            `

            placeholder.innerHTML = out;
        }
    })


document.getElementById('employee-list')?.addEventListener('click', (e) => {
    if (e.target.classList.contains("edit")) {
        let btn = e.target.id;

        fetch(url + "/" + e.target.parentElement.id)
            .then(response => response.json())
            .then((employee) => {
                console.log(employee);
                let modal = document.getElementById("myModal");
                let span = document.getElementsByClassName("close")[0];

                document.getElementById(btn).addEventListener('click', () => {
                    modal.style.display = "block";

                    modal.innerHTML = `
                <div class="modal-content">
                    <span class="close">&times;</span>
                  
                    <div class="container">
                    <form method="post" id="employee-add-form">
                        <div class="row">
                            <div class="col-half">
                                <h4>Title</h4>
                                <div class="input-group">
                                    <input type="text" id="title" name="title" placeholder=${employee.title} required="" value=${employee.title}>
                                </div>
                            </div>
                            <div class="col-half">
                                <h4>Full Name</h4>
                                <div class="input-group">
                                    <input type="text" id="name" name="name" placeholder=${employee.name} required="" value=${employee.name}>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-half">
                                <h4>Email</h4>
                                <div class="input-group">
                                    <input type="text" id="email" name="email" placeholder=${employee.email} required="" value=${employee.email}>
                                </div>
                            </div>
                            <div class="col-half">
                                <h4>Country</h4>
                                <div class="input-group">
                                    <input type="text" id="country" name="country" placeholder=${employee.country} required="" value=${employee.country}>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-half">
                                <h4>Nationality</h4>
                                <div class="input-group">
                                    <input type="text" id="nationality" name="nationality" placeholder=${employee.nationality} required="" value=${employee.nationality}>
                                </div>
                            </div>
                            <div class="col-half">
                                <h4>Contact No</h4>
                                <div class="input-group">
                                    <input type="text" id="contactNo" name="contactNo" placeholder=${employee.contactNo} required="" value=${employee.contactNo}>
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
            `
                    let form = document.querySelector('#employee-add-form');
                    form.addEventListener('submit', handleSubmit);
                })

                let result = JSON.stringify({});

                function handleSubmit(event) {
                    event.preventDefault();

                    const data = new FormData(event.target);

                    const title = data.get('title');
                    const name = data.get('name');
                    const email = data.get('email');
                    const dob = employee.dob;
                    const gender = employee.gender;
                    const country = data.get('country');
                    const nationality = data.get('nationality');
                    const contactNo = data.get('contactNo')

                    result = JSON.stringify({title, name, email, dob, gender, country, nationality, contactNo});

                    console.log(result);

                    let xhr = new XMLHttpRequest();
                    xhr.open("PUT", "http://localhost:8080/api/v1/employee/" + e.target.parentElement.id);

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


document.getElementById('employee-list')?.addEventListener('click', (e) => {

    if (e.target.classList.contains("delete")) {
        // Swal.fire({
        //     title: 'Are you sure?',
        //     text: "You won't be able to revert this!",
        //     icon: 'warning',
        //     showCancelButton: true,
        //     confirmButtonColor: '#3085d6',
        //     cancelButtonColor: '#d33',
        //     confirmButtonText: 'Yes, delete it!'
        //
        // }).then((result) => {
        //     if (result.isConfirmed) {
        if (confirm("Are you sure to Delete!")) {
            let id = e.target.parentElement.id;
            fetch(url + "/" + id.toString(), {method: 'DELETE'})
                .then(response => {
                    console.log(response.json());

                    // Swal.fire(
                    //     'Deleted!',
                    //     'Your file has been deleted.',
                    //     'success'
                    // );
                    location.reload();
                })
                .catch(error => {
                    console.error('There was an error!', error);
                });
        }

        //     }
        // })
    }
});

