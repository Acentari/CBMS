
let url = "http://192.168.1.108:8080/studentsapp-1.0-SNAPSHOT";
let username = localStorage.getItem("storageName");

const data1 = {["username"]: localStorage.getItem("storageName"),};
let json = JSON.stringify(data1);

const load = document.getElementById("load");
load.setAttribute('class', "spinner-border");
load.setAttribute('role', "status");

const xhr = new XMLHttpRequest();
xhr.open("GET", url+"/Serv2"+"?cached="+"null"+"&username="+username, true);
xhr.setRequestHeader("Content-Type", "application/json");
xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        // const myArr = JSON.parse(xhr.responseText);
        // document.getElementById('username').innerHTML = xhr.responseText;
        console.log("this is the response: "+xhr.responseText);
        const myArr = JSON.parse(xhr.responseText);
        console.log(myArr.tracks);
        trackList1(myArr);
        load.setAttribute('class', "");
    }
}
xhr.send();

function trackList1(myArr) {
    let maxLength = 30;
    let result = "";
    const row = document.createElement("div");
    row.setAttribute('class', "row");
    let j = 0;
    for (let i = 0; i < myArr.tracks.length; i++) {
        j++;
        let img = document.createElement("img");
        img.setAttribute('class', "img-thumbnail");
        img.setAttribute('id', "picture");

        if (myArr.covers[i] !== "none") {
            img.setAttribute('src', _arrayBufferToBase64(myArr.covers[i]));
        } else {
            img.setAttribute('src', "m.png");
        }

        const col = document.createElement("div");
        col.setAttribute('class', "col overflow-hidden");
        let s = myArr.tracks[i];
        if (s.length > maxLength) {
            result = s.substring(0, maxLength) + '...';
        } else {
            result = s;
        }

        const t = document.createTextNode(result);
        col.appendChild(img);
        col.appendChild(t);
        col.addEventListener('click', function () {
            const xhr1 = new XMLHttpRequest();
            const audio = document.getElementById('audio');
            const source = document.getElementById('audioSource');

            xhr1.open('GET', url+"/Serv1?username="+username+"&trackname="+myArr.tracks[i], true);
            xhr1.setRequestHeader('Content-Type', 'application/json');
            xhr1.responseType = 'blob';
            xhr1.onload = function(evt) {
                const blob = new Blob([xhr1.response], {type: 'audio/ogg'});
                const objectUrl = URL.createObjectURL(blob);
                source.src = objectUrl;
                // Release resource when it's loaded
                audio.onload = function(evt) {
                    URL.revokeObjectURL(objectUrl);
                };
                audio.load();
            };
            xhr1.send();

            // audio.play();
        });

        row.appendChild(col);
        if (j === 6) {
            j = 0;
            let w100 = document.createElement("div");
            w100.setAttribute('class', "w-100");
            row.appendChild(w100);
        }
    }
    document.getElementById('show').appendChild(row);

}

function _arrayBufferToBase64( buffer ) {
    let binary = '';
    const bytes = new Uint8Array(buffer);
    const len = bytes.byteLength;
    for (let i = 0; i < len; i++) {
        binary += String.fromCharCode( bytes[ i ] );
    }
    return "data:jpg" + ";base64," + window.btoa( binary );
}




