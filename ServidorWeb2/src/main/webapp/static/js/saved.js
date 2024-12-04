document.addEventListener("DOMContentLoaded", function () {
    const savedTheme = localStorage.getItem('themeSelected');
    if (savedTheme) {
        document.documentElement.setAttribute("data-bs-theme", savedTheme);
    }
});