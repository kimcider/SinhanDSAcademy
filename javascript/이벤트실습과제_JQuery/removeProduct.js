function removeProduct() {
    let tr = this.parentNode.parentNode;
    let tbody = tr.parentNode;
    tbody.removeChild(tr);
}