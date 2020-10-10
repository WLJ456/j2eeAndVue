<template>
  <div>
    <slide-verify
      ref="slideblock"
      :w="350"
      :h="200"
      @again="onAgain"
      @fulfilled="onFulfilled"
      @success="onSuccess"
      @fail="onFail"
      @refresh="onRefresh"
      :slider-text="text"
      :accuracy="accuracy"
    ></slide-verify>
    <div class="info">{{msg}}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      msg: '',
      text: '向右滑动->',
      // 精确度小，可允许的误差范围小；为1时，则表示滑块要与凹槽完全重叠，才能验证成功。默认值为5
      accuracy: 3,
      flag: false
    };
  },
  methods: {
    onSuccess(times) {
      this.msg = `验证成功！, 耗时${(times / 1000).toFixed(1)}s`;
      this.flag = true;
      this.$emit('toslide', this.flag);
    },
    onFail() {
      console.log('验证失败');
      this.msg = '';
      this.flag = false;
    },
    onRefresh() {
      console.log('点击了刷新小图标');
      this.msg = '';
      this.flag = false;
    },
    onFulfilled() {
      console.log('刷新成功啦！');
    },
    onAgain() {
      console.log('检测到非人为操作的哦！');
      this.msg = 'try again';
      this.flag = false;
      // 刷新
      this.$refs.slideblock.reset();
    }
  }
};
</script>

<style scoped>
</style>

