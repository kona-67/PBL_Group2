function toggleDrop() {
  document.getElementById('sortDrop').classList.toggle('open');
}

function pickSort(label) {
  document.getElementById('sortLabel').textContent = label;
  document.getElementById('sortDrop').classList.remove('open');
}

document.addEventListener('click', function(e) {
  if (!e.target.closest('.sort-wrap')) {
    document.getElementById('sortDrop').classList.remove('open');
  }
});
