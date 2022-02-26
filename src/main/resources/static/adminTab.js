
fetch("/api/users")
    .then(res=> {
        res.json().then(
            data => {
                console.log(data);
                var temp = "";
                data.forEach((u) => {
                    temp += "<tr>";
                    temp += "<td>" + u.id + "</td>";
                    temp += "<td>" + u.firstName + "</td>";
                    temp += "<td>" + u.lastName + "</td>";
                    temp += "<td>" + u.age + "</td>";
                    temp += "<td>" + u.email + "</td>";
                    temp += "<td>" + getRoles(u)+ "</td>";
                    temp += "<td>";
                    temp += '<button type="button" onclick="getModalEdit(' + u.id + ')" class="btn btn-primary btn-sm">Edit</button>'
                    temp += "</td>";
                    temp += "<td>";
                    temp += '<button type="button" onclick="getModalDelete(' + u.id + ')" class="btn btn-danger btn-sm">Delete</button>';
                    temp += "<td>";

                })
                document.getElementById("data").innerHTML = temp;
            })
    })

