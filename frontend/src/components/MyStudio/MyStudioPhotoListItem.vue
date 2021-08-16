<template>
  <div class="image">
    <div class="img img-vert" :id="photos.id">
      <img class="inner" :src="photo_path" alt="" />
      <h6>{{ photos.id }}</h6>
      <h6>{{ photos }}</h6>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyStudioPhotoListItem',
  props: {
    photos: Object,
  },
  data: function () {
    return {
      photo_path: this.photos.photo_path,
      photo_id: this.photos.id,
    }
  },
  mounted: function () {
    var ran_mar_1 = Math.random() * 20 + 5
    var ran_mar_2 = Math.random() * 20 + 5
    var ran_mar_3 = Math.random() * 30 + 5
    var ran_mar_4 = Math.random() * 30 + 5
    console.log(ran_mar_1)
    function randomMargin() {
      // eslint-disable-next-line no-undef
      $(`#${this.photo_id}`).css({
        'margin-top': ran_mar_1 + '%',
        'margin-bottom': ran_mar_2 + '%',
        'margin-left': ran_mar_3 + '%',
        'margin-right': ran_mar_4 + '%',
      })
    }
    setTimeout(randomMargin, 0)
  },
}
</script>

<style>
html,
body {
  padding: 0;
  margin: 0;
}

* {
  box-sizing: border-box;
  font-family: 'Open Sabs', sans-serif;
}

.wrapper {
  width: 100%;
  padding: 80px 80px;
  background: whil;
  text-align: center;
}

.list {
  width: 100%;
  max-width: 1200px;
  display: inline-flex;
  flex-direction: row;
}

.left,
.right {
  display: inline-block;
  width: 50%;
}

.info {
  position: absolute;
  opacity: 0;
  visibility: hidden;
  transition: opacity 0.25s ease-in-out, transform 0.25s ease-in-out,
    visibility 0.25s ease-in-out;
  transform: translateY(-30%);
  top: calc(50%);
  z-index: 2;
}
.left .info {
  left: 66.66666%;
  left: 100%;
}
.right .info {
  right: 66.666666%;
  right: 100%;
}
.image:hover .info,
.image.active .info {
  opacity: 1;
  transform: translateY(-50%);
  visibility: visible;
}
.image.active .info {
  transition-delay: 0.5s;
  opacity: 0;
  visibility: hidden;
  transform: translateY(-30%);
}
.info-title {
  margin: 0;
  text-transform: uppercase;
  font-weight: 600;
  text-align: left;
  font-size: 140px;
  white-space: nowrap;
  color: #242424;
  mix-blend-mode: difference;
  line-height: 0.9em;
  overflow: hidden;
}
.left .info-title {
  transform: translateX(-33.333333%);
  text-align: left;
}
.right .info-title {
  transform: translateX(33.333333%);
  text-align: right;
}
.img .info-title {
  color: white;
}
.info-tagline,
.info-view {
  margin: 0;
  text-align: left;
  text-transform: uppercase;
  font-weight: 400;
  letter-spacing: 0.1em;
}
.left .info-tagline,
.left .info-view {
  margin-left: 10px;
  text-align: left;
}
.right .info-tagline,
.right .info-view {
  margin-right: 10px;
  text-align: right;
}
.info-tagline {
  font-size: 18px;
  letter-spacing: 0.5em;
}
.info-view {
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  letter-spacing: 0.25em;
  color: #646464;
}
.right .info-view {
  justify-content: flex-end;
}
.info-view .line {
  width: 50px;
  height: 2px;
  display: inline-block;
  margin: 0;
  margin-right: 10px;
  border: none;
  border-bottom: 1px solid #aaa;
  border-radius: 15rem;
}
.right .info-view .line {
  margin-right: 0;
}

.img {
  position: relative;
  z-index: 10;
  width: 100%;
  overflow: hidden;
}
.img::before {
  transition: background-color 0.25s ease-in-out;
  display: block;
  content: '';
  background-color: rgba(0, 0, 0, 0);
}
.image:hover .img::before {
  background-color: rgba(0, 0, 0, 0.2);
}
.image.active .img::before {
  background-color: rgba(0, 0, 0, 0);
}
.img-vert::before {
  padding-bottom: 100%;
}
.img-hor::before {
  padding-bottom: 62.5%;
}
.img-square::before {
  padding-bottom: 100%;
}

.inner {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  z-index: -1;
}

.image {
  position: relative;
  visibility: visible;
  transition: visibility 0.5s ease-in-out;
}
.image:hover {
  cursor: pointer;
}
.image.close {
  visibility: hidden;
}

.panel {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  transform-origin: left 50%;
  background: white;
  z-index: 10;
  transform: scaleX(1);
  transition: transform 0.5s ease-in-out;
}
.close .panel {
  transition-delay: 0s !important;
  transform: scaleX(1) !important;
}

.image-1 {
  width: 100%;
  margin-top: 67.708333%;
}
.image-1 .info {
  top: 37.5%;
}
.image-1:nth-child(1) {
  margin-top: 0%;
}

.image-2 {
  width: 100%;
  margin-top: 46.875%;
  transform: translateX(-16.6666666667%);
}

.image-3 {
  margin-top: 52.0833325%;
  width: 100%;
  transform: translateX(16.6666666667%);
}

.image-4 {
  margin-top: 68.75%;
  transform: translateX(-25%);
  width: 133.333333%;
}

.image-5 {
  width: 66.666666%;
  margin-top: 52.083333125%;
  margin-left: 33.3333333333%;
}

.image-6 {
  width: 83.333333%;
  margin-top: 52.0833325%;
}
.image-6 .info {
  top: 58.9285714286%;
}

/* .right .image:nth-child(3n + 1) .inner {
  background-image: url('https://images.unsplash.com/photo-1496749843252-699a989877a1?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=fe5da9650707e5a93c8c3cf164c2e74b&auto=format&fit=crop&w=1375&q=80');
}
.right .image:nth-child(3n + 2) .inner {
  background-image: url('https://images.unsplash.com/photo-1492970471430-bc6bd7eb2b13?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=9893bc89e46e2b77a5d8c091fbba04e9&auto=format&fit=crop&w=1355&q=80');
}
.right .image:nth-child(3n) .inner {
  background-image: url('https://images.unsplash.com/photo-1501707305551-9b2adda5e527?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=0cf5887247e17503ce4e542d00d86b9d&auto=format&fit=crop&w=1335&q=80');
  background-position-x: 50%;
}

.left .image:nth-child(3n + 1) .inner {
  background-image: url('https://images.unsplash.com/photo-1513555329264-541740eca390?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=356f530d44af9c3c2690fd464baa655c&auto=format&fit=crop&w=1350&q=800');
  width: 100%;
  height: auto;
}
.left .image:nth-child(3n + 2) .inner {
  background-image: url('https://images.unsplash.com/photo-1510022079733-8b58aca7c4a9?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=6da2c9e985e56b6a43209d7b1d46c8ca&auto=format&fit=crop&w=701&q=80');
}
.left .image:nth-child(3n) .inner {
  background-image: url('https://images.unsplash.com/photo-1521089815383-cf2b2cf7f0f0?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=524500bf8ae21941d5af74e374d43dec&auto=format&fit=crop&w=701&q=80');
  background-position-x: 50%;
} */
</style>
