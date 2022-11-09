let result = JSON.stringify({});

function handleSubmit(event) {
    event.preventDefault();

    const data = new FormData(event.target);

    const title = data.get('title');
    const name = data.get('name');
    const email = data.get('email');
    const dob = data.get('date');
    const gender = data.get('gender');
    const country = data.get('country');
    const nationality = data.get('nationality');
    const contactNo = data.get('contactNo')

    result = JSON.stringify({title, name, email, dob, gender, country, nationality, contactNo});

    console.log(result);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/v1/employee");

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

const form = document.querySelector('#employee-add-form');
form.addEventListener('submit', handleSubmit);

