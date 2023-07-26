function frmCheck() {
    let userid= document.querySelector(".userid")
    let username= document.querySelector(".username")
    let password= document.querySelector(".password")
    let level= document.querySelector(".level")

    if(!userid.value) {
        alert("아이디를 입력해주세요.");
        userid.focus();
        return false;
    }

    if(!username.value) {
        alert("사용자의 ID를 입력해주세요.");
        username.focus();
        return false;
    }

    // 키값 = html: 벨류값 = dto이름
    let obj = {
        "userid": userid.value,
        "username": username.value,
        "password": password.value,
        "level": level.value
    };

    $.ajax({
        type: "post",
        url: "/register",
        dataType: "json",
        data: obj,
        success: function(res) {
            if(res.msg == "success") {
                alert("회원가입이 완료 되었습니다.");
                location.href = "/list";
            }
        },
        error: function(err) {
            console.log(err);
        }
    });
}