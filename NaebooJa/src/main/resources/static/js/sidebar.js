const sidebar = document.querySelector(".sidebar");
const linkItems = document.querySelectorAll(".link-item");
const darkMode = document.querySelector(".dark-mode");

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
  if (!linkItems[i].classList.contains("dark-mode")) {
    linkItems[i].addEventListener("click", (e) => {
      linkItems.forEach((linkItem) => {
        linkItem.classList.remove("active");
      });
      linkItems[i].classList.add("active");
    });
  }
}

