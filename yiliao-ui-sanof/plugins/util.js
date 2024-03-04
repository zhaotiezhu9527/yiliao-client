export function create5star(cxt, sx, sy, radius, color, rotato) {
  cxt.save();
  cxt.setFillStyle(color);
  cxt.translate(50, 50);
  cxt.beginPath();
  var dig = (Math.PI / 5) * 4;
  for (var i = 0; i < 5; i++) {
    //画五角星的五条边
    var x = Math.sin(i * dig);
    var y = Math.cos(i * dig);
    cxt.lineTo(x * radius, y * radius);
  }
  cxt.closePath();
  cxt.stroke();
  cxt.fill();
  cxt.restore();
}
export function CanvasYinzhang(canvasId, companyName, text) {
  let context = uni.createCanvasContext(canvasId);
  context.setLineCap("round"); //设置线两端端点样式更加圆润
  context.setLineJoin("round"); //设置两条线连接处更加圆润
  context.setStrokeStyle("#ff0000"); //设置线的颜色
  context.setLineWidth(1); //设置线条的宽度
  context.moveTo(95, 50);
  context.arc(50, 50, 45, 0, Math.PI * 2); //宽、高、半径
  context.stroke();
  // 绘制五角星
  create5star(context, 100, 100, 10, "#f00", 0);
  context.setFontSize("10");
  context.setTextBaseline("middle");
  context.setTextAlign("center");
  context.setLineWidth(1);
  context.setFillStyle("#f00");
  context.strokeText(text, 50, 80);
  // 绘制印章单位
  context.translate(50, 50);
  context.font = "10px 黑体";
  var count = companyName.length;
  var angle = (4 * Math.PI) / (3 * (count - 1));
  var chars = companyName.split("");
  var c;
  for (var i = 0; i < count; i++) {
    c = chars[i];
    if (i == 0) {
      context.rotate((5 * Math.PI) / 6);
    } else {
      context.rotate(angle);
    }
    context.save();
    context.translate(50, 0);
    context.rotate(Math.PI / 2);
    context.setFillStyle("#ff0000");
    context.fillText(c, 0, 15);
    context.restore();
  }
  setTimeout(() => {
    context.draw();
  }, 200);
}
