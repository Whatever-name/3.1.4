
$(async function () {
    await getTableWithUsers();
    //getNewUserForm();
   // getDefaultModal();
    //addNewUser();
})


const userFetchService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=utf-8'
    },

    findAllUsers: async () => await fetch('api/users'),
    //findOneUser: async (id) => await fetch(`api/users/${id}`),
    addNewUser: async (user) => await fetch('api/users', {method: 'POST', headers: userFetchService.head, body: JSON.stringify(user)}),
    updateUser: async (user, id, editRoles) => await fetch(`api/edit/?selectedRoles=` + editRoles, {method: 'PATCH', headers: userFetchService.head, body: JSON.stringify(user)}),
    deleteUser: async (id) => await fetch(`api/users/${id}`, {method: 'DELETE', headers: userFetchService.head})
}

async function getTableWithUsers() {
    let table = $('#data tbody');
    table.empty();

    await userFetchService.findAllUsers()
        .then(res => res.json())
        .then(users => {
            users.forEach(user => {
                let tableFilling = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.age}</td>
                            <td>${user.email}</td>
                            <td>${getRoles(user)}</td>
                            <td> <a href="/api/${user.id}" class="btn btn-primary editBtn">Edit</a> </td>
                            <td> <a href="/api/${user.id}" class="btn btn-danger ">Delete</a> </td> 
                            </td>
                            <td>
                            </td>
                            </tr>
                )`;
                table.append(tableFilling);
            })
        })
    document.onclick = function (event) {
        if ($(event.target).hasClass('editBtn')) {
            event.preventDefault();
            let href = $(event.target).attr("href");

            $.get(href, function (user) {
                $('.editForm #idEdit').val(user.id);
                $('.editForm #firstnameEdit').val(user.firstName);
                $('.editForm #lastnameEdit').val(user.lastName);
                $('.editForm #ageEdit').val(user.age);
                $('.editForm #emailEdit').val(user.email);
                $('.editForm #passwordEdit').val(user.password);
            });

            $('.editForm #editModal').modal();
        }
        if ($(event.target).hasClass('editSuccess')) {
            event.preventDefault();

            let user = {
                id: $('#idEdit').val(),
                firstName: $('#firstnameEdit').val(),
                lastName: $('#lastnameEdit').val(),
                age: $('#ageEdit').val(),
                email: $('#emailEdit').val(),
                password: $('#passwordEdit').val(),
            }

            let ids = user.id
            var selectedRoles = [];
            var selectedRoles = $('#ROLES option:selected')
                .toArray().map(item => item.text);

            userFetchService.updateUser(user, ids, selectedRoles);
            getTableWithUsers();
            $(".editForm #editClose").click()
        }
    }
}

