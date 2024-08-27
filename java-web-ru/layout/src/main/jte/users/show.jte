@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <p>First Name: ${page.getUser().getFirstName()}</p>
    <p>Last Name: ${page.getUser().getLastName()}</p>
    <p>Id: ${page.getUser().getId()}</p>
    <p>Email: ${page.getUser().getEmail()}</p>
`
)