@import gg.jte.Content
@param Content content

<!doctype html>
<html lang="en">

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>

<body>
<p class="navbar-header">
    <h1 class="navbar-brand">🏠 <a href="/">Home</a></h1>
    <h1 class="navbar-brand">👥 <a href="/users">Users</a></h1>
</p>
${content}
<p>
    <a href="https://github.com/faraway10">faraway10 github</a>
</p>
</body>

</html>
