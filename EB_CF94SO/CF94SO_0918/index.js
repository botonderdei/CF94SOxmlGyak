var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
        myFunction(this);
    }
};
xmlhttp.open("GET", "https://users.iit.uni-miskolc.hu/~erdei11/XY_orarend.xml", true);
xmlhttp.send();

function myFunction(Doc) {
    xmlDoc = parser = new DOMParser();
    xmlDoc = parser.parseFromString(Doc.response,"text/xml");
    console.log(xmlDoc);
    for (let i = 8; i <= 18; i++) {
        let tr = document.createElement("tr");
        let h = document.createElement("td");
        h.innerText = `${i}-${i + 1}`;
        tr.appendChild(h);
        for (const days of ["Hetfo", "Kedd", "Szerda", "Csutortok", "Pentek"]) {
            let td = document.createElement("td");
            tr.appendChild(td);
            for (let j = 0; j < xmlDoc.childNodes[0].children.length; j++) {
                let day = xmlDoc.childNodes[0].children[j].children[1];
                if (day.children[0].textContent == days && day.children[1].textContent <= i && day.children[2].textContent > i) {
                    td.innerText = day.parentElement.children[0].textContent;

                }
            }
        }
        document.getElementById("orarend").appendChild(tr);
    }
}