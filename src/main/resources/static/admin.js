$(document).ready(); {
    AllUsersTable()
    UserTable()
}

function AllUsersTable() {
    fetch('http://localhost:8181/rest/admin').then(
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

function UserTable() {
    fetch('http://localhost:8181/rest/user').then(
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

$('#formNewUser').submit(function () {
    $.post(
        '/rest/create',
        $('#formNewUser').serialize(),
        function (userNew) {
            document.location.href = userNew;
        });
    return false;
});

function getModalEdit(id) {
    $.get('/rest/admin/' + id, function (usr) {
        $('#idEditUser').val(usr.id)
        $('#firstNameEditUser').val(usr.firstName);
        $('#lastNameEditUser').val(usr.lastName);
        $('#ageEditUser').val(usr.age);
        $('#emailEditUser').val(usr.email);
        $('#roleDeleteUser').val(usr.roles);
        $('#passwordEditUser').val(usr.password);
    });
}

$('#formEdit').submit(function () {
    $.post(
        '/rest/edit',
        $('#formEdit').serialize(),
        function (editUser) {
            document.location.href = editUser;
        });
    return false;
});

function getModalDelete(id) {
    $.get('/rest/admin/' + id, function (usr) {
        $('#idDeleteUser').val(id);
        $('#firstNameDeleteUser').val(usr.firstName);
        $('#lastNameDeleteUser').val(usr.lastName);
        $('#ageDeleteUser').val(usr.age);
        $('#emailDeleteUser').val(usr.email);
        $('#roleDeleteUser').val(usr.roles);
        $('#passwordDeleteUser').val(usr.password);
    });
}

$('#modalDelete').click(function () {
    let id = $('#idDeleteUser').val();
    $.ajax({
        url: '/rest/delete/' + id,
        type: 'DELETE',
        dataType: 'text',
    }).done(() => {
        $('#modalDelete').modal('hide')
        return AllUsersTable();
    });
});
