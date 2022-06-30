$(document).ready(); {
    setInterval('getAllUsersTable()', 1000)
    getUserTable()
}

function getAllUsersTable() {
    fetch('http://localhost:8080/api/admin').then(
        response => {
            response.json().then(
                data => {
                    let str = '';
                    data.forEach(usr => {
                        str += `<tr>
                               <td> ${usr.id} </td>
                               <td> ${usr.firstName} </td>
                               <td> ${usr.lastName} </td>
                               <td> ${usr.age} </td>
                               <td> ${usr.email} </td>
                               <td> ${usr.roles.map(e => ' ' + e.role?.substring(0))} </td>
                               <td><button type="button" onclick="getModalEdit(${usr.id})" class="btn btn-info" data-toggle="modal" data-target="#modalEdit">Edit</button></td>
                               <td><button type="button" onclick="getModalDelete(${usr.id})" class="btn btn-danger" data-toggle="modal" data-target="#modalDelete">Delete</button></td>
                               </tr>`;
                    });
                    $('#tableAllUsers tbody').empty().append(str);
                });
        });
}

function getUserTable() {
    fetch('http://localhost:8080/api/user').then(
        response => {
            response.json().then(
                data => {
                    let str = `<tr> 
                        <td> ${data.id} </td>
                        <td> ${data.firstName} </td>
                        <td> ${data.lastName} </td>
                        <td> ${data.age} </td>
                        <td> ${data.email} </td>
                        <td> ${data.roles.map(e => ' ' + e.role?.substring(0))} </td>
                        </tr>`;

                    $('#panelUser tbody').empty().append(str);
                });
        });
}

function postNewUser() {
    fetch('http://localhost:8080/api/create', {
        method: 'POST',
        body: new FormData(document.getElementById('formNewUser'))
    })
}

function getModalEdit(id) {
    fetch('http://localhost:8080/api/admin/' + id).then(
        response => {
            response.json().then(
                usr => {
                    $('#idEditUser').val(usr.id)
                    $('#firstNameEditUser').val(usr.firstName);
                    $('#lastNameEditUser').val(usr.lastName);
                    $('#ageEditUser').val(usr.age);
                    $('#emailEditUser').val(usr.email);
                    $('#passwordEditUser').val(usr.password);
                });
        });
}

function putEditUser() {
    fetch('http://localhost:8080/api/edit', {
        method: 'PUT',
        body: new FormData(document.getElementById('formEdit'))
    }).then(
        $('#modalEdit').modal('hide'),
    )
}

function getModalDelete(id) {
    fetch('http://localhost:8080/api/admin/' + id).then(
        response => {
            response.json().then(
                usr => {
                    $('#idDeleteUser').val(usr.id)
                    $('#firstNameDeleteUser').val(usr.firstName);
                    $('#lastNameDeleteUser').val(usr.lastName);
                    $('#ageDeleteUser').val(usr.age);
                    $('#emailDeleteUser').val(usr.email);
                    $('#passwordDeleteUser').val(usr.password);
                });
        });
}

function deleteUser() {
    let id = $('#idDeleteUser').val();
    fetch('http://localhost:8080/api/delete/' + id, {
        method: 'DELETE'
    }).then(
        $('#modalDelete').modal('hide'),
    )
}