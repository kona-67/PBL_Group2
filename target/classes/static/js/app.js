
/* 共通の画面遷移プログラム */
const moveAddBtn = document.getElementById("moveAddBtn");
const moveHistoryBtn = document.getElementById("moveHistoryBtn");
const moveHomeBtn = document.getElementById("moveHomeBtn");
const moveLoginBtn = document.getElementById("moveLoginBtn");

if(moveAddBtn){
    moveAddBtn.addEventListener("click", () =>{//薬の登録画面に遷移
        window.location.href = "/add-page";
    })
}

if(moveHistoryBtn){
    moveHistoryBtn.addEventListener("click", () =>{//履歴画面に遷移
        window.location.href = "/history";
    })
}

if(moveHomeBtn){
    moveHomeBtn.addEventListener("click", () =>{//ホーム画面に遷移
        window.location.href = "/home";
    })
}

if(moveLoginBtn){
    moveLoginBtn.addEventListener("click", () =>{//ログイン画面に遷移
        window.location.href = "/user/login";
    })
}
/* ここまで */

/* ここからadd.html */
/* ここまで */

/* ここからedit.html */
/* ここまで */

/* ここからhistory.html */
/* ここまで */

/* ここからhome.html */
/* ここまで */

/* ここから list.htmlの処理 */
const moveEdit = document.getElementById("tableBody");

if(moveEdit){
    moveEdit.addEventListener("DOMContentLoaded", () => {
    const rows = moveEdit.querySelectorAll("#tableBody .row");

    rows.forEach(row => {
        row.addEventListener("click", () => {
            const id = row.dataset.id;
            window.location.href = `/edit/${id}`;
        });
    });
});
}
/* ここまで */

/* ここからlogin.html */
const loginBtn = document.getElementById("sendBtn");

if(loginBtn){
    loginBtn.addEventListener("click", () =>{

    })
}
/* ここまで */

/* ここからregister.html */
/* ここまで */