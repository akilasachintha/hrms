const url = 'http://localhost:8080/api/v1/jobleave';

document.querySelector('#leave-list').innerHTML = `<div class="loader"></div>`;

fetch(url)
    .then(response => response.json())
    .then((jobleaves) => {
        let placeholder = document.querySelector("#leave-list");
        let out = "";

        for (let jobleave of jobleaves) {
            out += `
            <tr>
                                <td>${jobleave.leaveId}</td>
                                <td>
                                    <a class="text-heading font-semibold" href="#">
                                        ${jobleave.name}
                                    </a>
                                </td>
                                <td>
                                    ${jobleave.fromDate}
                                </td>
                                <td>
                                    ${jobleave.toDate}
                                </td>
              
                                <td>
                                    ${jobleave.description}
                                </td>
                                <td class="text-end" id=${jobleave.leaveId}>
                                    <button class="btn btn-sm btn-neutral edit" id="leave-edit-btn-${jobleave.leaveId}">Edit</a>
                                    <button type="button" class="btn btn-sm btn-square btn-neutral text-danger-hover bi bi-trash delete">
                                     
                                    </button>
                                </td>
                            </tr>
            `

            placeholder.innerHTML = out;
        }
    })


document.getElementById('leave-list')?.addEventListener('click', (e) => {
    if (e.target.classList.contains("edit")) {
        let btn = e.target.id;

        console.log(btn);

        fetch(url + "/" + e.target.parentElement.id)
            .then(response => response.json())
            .then((jobleaves) => {
                console.log(jobleaves);
                let modal = document.getElementById("myModal");
                let span = document.getElementsByClassName("close")[0];

                document.getElementById(btn).addEventListener('click', () => {
                    modal.style.display = "block";

                    modal.innerHTML = `

            <div class="modal-content">
                <span class="close">&times;</span>
                <div class="container">
                    <form method="post" id="leave-add-form">
             
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
                    </form>
                </div>
            </div>
            `
                    let form = document.querySelector('#leave-add-form');
                    form.addEventListener('submit', handleSubmit);
                })

                let result = JSON.stringify({});

                function handleSubmit(event) {
                    event.preventDefault();

                    const data = new FormData(event.target);

                    const empId = jobleaves.empId;
                    const fromDate = data.get('fromDate');
                    const toDate = data.get('toDate');
                    const description = data.get('description');

                    result = JSON.stringify({empId, fromDate, toDate, description});

                    console.log(result);

                    let xhr = new XMLHttpRequest();
                    xhr.open("PUT", "http://localhost:8080/api/v1/jobleave/" + e.target.parentElement.id);

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

document.getElementById('leave-list')?.addEventListener('click', (e) => {

    if (e.target.classList.contains("delete")) {

        if (confirm("Are you Sure to Delete")) {
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

    }
});

