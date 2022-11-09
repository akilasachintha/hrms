let result = JSON.stringify({});

function handleSubmit(event) {
    event.preventDefault();

    const data = new FormData(event.target);

    const email = data.get('email');
    const password = data.get('password');


    result = JSON.stringify({email, password});

    console.log(result);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/v1/auth/login");

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = () => {
        let res = JSON.parse(xhr.responseText);
        console.log(JSON.parse(xhr.responseText));
        if(res.status === "Success"){
            location.replace("dashboard_main.html");
            console.log("Hello");
            // Swal.fire({
            //     title: 'Success!',
            //     text: 'Successfully logged',
            //     icon: 'success',
            //     confirmButtonText: 'Done'
            // }).then(() => {
            //
            // })
        }else {
            location.replace("login.html");
            // Swal.fire({
            //     title: 'Login Error!',
            //     text: 'Error logged',
            //     icon: 'error',
            //     confirmButtonText: 'Done'
            // })
        }
    };

    xhr.send(result);
}

const form = document.querySelector('#login');
form.addEventListener('submit', handleSubmit);

