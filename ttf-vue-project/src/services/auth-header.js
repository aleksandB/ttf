export default function authHeader() {
    let user = JSON.parse(localStorage.getItem('user'));
    if (user && user.accessToken) {
        console.log({ Authorization: 'Bearer' + user.accessToken})
        return { Authorization: 'Bearer' + user.accessToken}       
    } else {
        return {};
    }
}