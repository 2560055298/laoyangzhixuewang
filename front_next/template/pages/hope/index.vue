<template>
  <div id="bbody">
    <br><br><br>
    <div id="app">
      <div id="title">
        <p id="title01">愿景</p>
        <span id="title02">-- 老洋</span>
        <hr style="background-color:black; height:1px; border:none;">
      </div>
      <div id="content">
        <p>要是没有愿景，生活似乎也不会怎样。 </p>
        <p>但是，有了愿景的生活, 可能会不一样。</p>
        <p>不一样在哪里呢？ </p>
        <p>不一样在：精神状态上。</p>
        <p>有愿景的他。</p>
        <p>似乎总是知道, 每天该做点什么。</p>
        <p>他有：详细可行的Flag。</p>
        <p>像程序一样，有条不紊的执行。</p>
        <p>只要进行Input, 一定会有Output。</p>
        <p>尽管大多数愿景, 结果并不如意。</p>
        <p>但, 这是他心驰神往的愿景呀~</p>
        <p>他认定没有AC, 岂过乌江？</p>
        <p>每个人都会有自己的个人愿景。</p>
        <p>但个人愿景, 常常只适用于个人。</p>
        <p>所以, 莫用继承思想, 给他人安排愿景。</p>
        <p>因为, 愿景重写后, 可能会遇到多态。</p>
        <p>多态很复杂，也许转角就遇到赵高。</p>
        <p>所以, 本站的愿景也只属于：个人愿景。</p>
        <p>仅是邀君一览：《劝学诗》。</p>
        <p>共赏：时间、空间复杂度。</p>
      </div>
    </div>
    <br><br>
  </div>
</template>

<style>
#bbody {
  /*设置背景：图片*/
  background-image:url('../../assets/img/g.jpg');
  /*设置背景图片：大小*/
  /* background-size:1420px 1300px; */

  width: 100%; height: auto;max-width: 100%; display: block;
}

/*设置段落：样式*/
p {
    color: black;       /*设置字体为：黑色*/
    margin: 11px;       /*设置：外边距（上、下、左、右）都为11px*/
}

/*设置h1标题样式*/
h1 {
    font-size: 22px;    /*设置：字体大小*/
    margin: 0;          /*设置外边距为：0*/
}

/*设置：中间div （文本框）样式*/
#app{
    width: 300px;               /*设置div：宽度*/
    height:760px;               /*设置div：高度*/
    margin: auto;               /*设置div：居中*/
    text-align: center;         /*div文本框：文字居中*/
    border: 1px solid;          /*设置div：边框*/
    margin-top: -10px;
}

/* 为<div id="title"> 设置样式*/
#title{
    width: 300px;
    display: inline-block;
    letter-spacing: 3px;
}

/*为：<p id="title01">写给过去的诗</p>   设置样式*/
#title01{
    font-size: 18px;
    margin: 0px;
    margin-top: 10px;

}

/*为： <span id="title02">-- 老洋</span>  设置样式*/
#title02{
    display: inline-block;
    font-size: 14px;
    font-weight: 500;
    width: 200px;
    text-align: right;
}

/*为：<div id="content"> 设置样式*/
#content{
    display: inline-block;      /*即使内联元素、又是块级元素*/
    font-size: 13px;            /*设置字体：大小*/
    margin-top: -10px;
}

</style>
