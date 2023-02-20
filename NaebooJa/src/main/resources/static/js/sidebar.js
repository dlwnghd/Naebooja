const menuItems = document.querySelectorAll('.menu-item');
const sidebar = document.querySelector('.sidebar');

menuItems.forEach((item) => {
  item.addEventListener('click', (event) => {
    menuItems.forEach((item) => {
      item.classList.remove('active');
    });
    event.currentTarget.classList.add('active');
  });
});

sidebar.addEventListener('mouseover', () => {
  sidebar.classList.remove('collapsed');
});

sidebar.addEventListener('mouseout', () => {
  sidebar.classList.add('collapsed');
});