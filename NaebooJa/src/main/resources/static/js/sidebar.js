const sidebar = document.querySelector(".sidebar");
const linkItems = document.querySelectorAll(".link-item");

const link = document.location.href.substring(22, document.location.href.length);

for (let i = 0; i < linkItems.length; i++) {
    if(linkItems[i].innerHTML.search(link) > 0){
        linkItems[i].classList.add("active");
    }
}


//sidebar Hover
sidebar.addEventListener("mouseenter", () => {
  sidebar.classList.add("active");
});

//sidebar Hover Leave
sidebar.addEventListener("mouseleave", () => {
  sidebar.classList.remove("active");
});

//Link-items Clicked
for (let i = 0; i < linkItems.length; i++) {
    linkItems[i].addEventListener("click", (e) => {
      linkItems.forEach((linkItem) => {
        linkItem.classList.remove("active");
      });
      linkItems[i].classList.add("active");
    });
}

