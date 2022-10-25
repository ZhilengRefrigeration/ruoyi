let style
let clearWaterMark = () => {
  if (style) style.remove
}
export default function createWaterMark(str,_height) {
  clearWaterMark()
  if (!str) return
  let width = window.parseInt(document.body.clientWidth),
    canvasWidth = width / window.parseInt(width / 400),
    fontFamily = window.getComputedStyle(document.body)['font-family'],
    canvas = document.createElement('canvas')
  canvas.width = canvasWidth
  canvas.height = 200
  let cxt = canvas.getContext('2d')
  cxt.rotate((-20 * Math.PI) / 180)
  cxt.font = `18px${fontFamily}`
  cxt.fillStyle = 'rgba(8,8,8,0.2)'
  cxt.fillText(str, 50, 200)
  let imgSrc = canvas.toDataURL('image/png')
  style = document.createElement('style')
  style.innerHTML = `.with-watermark:before{
        content:"";
        width:100%;
        pointer-events:none;
        height:${_height}px;
        display:block;
        position:absolute;
        z-index:99999;
        background-image:url("${imgSrc}")
    }`
  ;(document.head.append || document.head.appendChild).apply(document.head, [style])
}
