
/* 共通の画面遷移プログラム */
const moveAddBtn = document.getElementById("moveAddBtn");
const moveHistoryBtn = document.getElementById("moveHistoryBtn");
const moveHomeBtn = document.getElementById("moveHomeBtn");
const moveLoginBtn = document.getElementById("moveLoginBtn");
const moveListBtn = document.getElementById("moveListBtn");

if(moveAddBtn){
    moveAddBtn.addEventListener("click", () =>{//薬の登録画面に遷移
        window.location.href = "/add-page";
    })
}

if(moveHistoryBtn){
    moveHistoryBtn.addEventListener("click", () =>{//履歴画面に遷移
        window.location.href = "/history-list";
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

if(moveListBtn){
    moveListBtn.addEventListener("click", () =>{//薬の一覧画面に遷移
        window.location.href = "/list";
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

const addNotificationBtn = document.getElementById("addNotificationBtn");
const tableBody = document.getElementById("notificationTableBody");

if (addNotificationBtn && tableBody) {

    addNotificationBtn.addEventListener("click", () => {

        const index = tableBody.rows.length;

        const row = document.createElement("tr");

        row.innerHTML = `
            <td>
                <input type="text"
                    name="notifications[${index}].name"
                    placeholder="薬名">
            </td>

            <td>
                <input type="time"
                    name="notifications[${index}].time">
            </td>

            <td>

                <input
                    type="hidden"
                    class="weekdayValue"
                    name="notifications[${index}].weekday">

                <label>
                    <input type="checkbox"
                        class="weekdayCheckbox"
                        value="月">
                    月
                </label>
                <label>
                    <input type="checkbox"
                        class="weekdayCheckbox"
                        value="火">
                    火
                </label>
                <label>
                    <input type="checkbox"
                        class="weekdayCheckbox"
                        value="水">
                    水
                </label>
                <label>
                    <input type="checkbox"
                        class="weekdayCheckbox"
                        value="木">
                    木
                </label>
                <label>
                    <input type="checkbox"
                        class="weekdayCheckbox"
                        value="金">
                    金
                </label>
                <label>
                    <input type="checkbox"
                        class="weekdayCheckbox"
                        value="土">
                    土
                </label>
                <label>
                    <input type="checkbox"
                        class="weekdayCheckbox"
                        value="日">
                    日
                </label>
            </td>

            <td>
                <input type="hidden"
                    class="enabledValue"
                    name="notifications[${index}].enabled"
                    value="false">

                <input type="checkbox"
                    class="enabledCheckbox"
                    checked>
            </td>

            <td>
                <button type="button" class="deleteBtn">
                    削除
                </button>
            </td>
        `;

        tableBody.appendChild(row);

    });

    tableBody.addEventListener("click", (event) => {

        if (event.target.classList.contains("deleteBtn")) {

            event.target.closest("tr").remove();

        }

    });

}

const notificationForm = document.querySelector("form");

if(notificationForm){

    notificationForm.addEventListener("submit", function(){

        document.querySelectorAll("tr").forEach(function(row){

            let checks =
                row.querySelectorAll(".weekdayCheckbox");

            let hidden =
                row.querySelector(".weekdayValue");


            if(checks.length > 0 && hidden){

                let days = [];

                checks.forEach(function(check){

                    if(check.checked){
                        days.push(check.value);
                    }

                });

                hidden.value = days.join(",");

            }

        });

        // ON/OFFをtrue,falseへ変換
        document.querySelectorAll(".enabledCheckbox")
        .forEach(function(check){

            let hidden =
                check.previousElementSibling;

            if(check.checked){

                hidden.value = "true";

            }else{

                hidden.value = "false";

            }

        });
    });

}
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