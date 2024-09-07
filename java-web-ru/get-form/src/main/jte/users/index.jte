@import exercise.dto.users.UsersPage
@param UsersPage page

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
</head>
<body>
<h1>Users</h1>
<form action="/users" method="get">
    <input type="search" required name="term">
    <input type="submit" value="Search">
</form>
@if(page.getUsers().isEmpty())
    <p>Still no users</p>
@else
    @for(var user : page.getUsers())
        <div>
            <h2>${user.getFirstName()} ${user.getLastName()}</h2>
        </div>
    @endfor
@endif
</body>
</html>