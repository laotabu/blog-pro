<template>
  <body id="poster">
  <el-form :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>

    <el-form-item prop="code">
      <el-input size="normal" type="text" v-model="loginForm.code" auto-complete="off"
                placeholder="点击图片更换验证码" @keydown.enter.native="submitLogin" style="width: 250px" ></el-input>
      <img :src="vcUrl" @click="updateVerifyCode" alt="" style="cursor: pointer">
    </el-form-item>

<!--    <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>-->
    <el-form-item style="width: 100%">
      <el-button type="primary" @click.native.prevent="submitClick" style="width: 100%;  background-color:#DEB887">登录</el-button>
    </el-form-item>

  </el-form>
  </body>
</template>
<script>
  import {postRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  export default{
    data(){
      return {
        // vcUrl: '/verifyCode?time='+new Date(),
        vcUrl: '/verifyCode?time='+new Date(),
        rules: {
          account: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          checkPass: [{required: true, message: '请输入密码', trigger: 'blur'}],
          code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
        },
        checked: true,
        loginForm: {
          username: 'sang',
          password: '123',
          code: ''
        },
        loading: false
      }
    },
    methods: {
      submitClick: function () {
        var _this = this;
        this.loading = true;
        postRequest('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password,
          code: this.loginForm.code
        }).then(resp=> {
          _this.loading = false;
          console.log(resp.data);
          if (resp.status == 200) {
            //成功
            var json = resp.data;
            // 判断是否为超级管理员
            this.$root.isSuperAdmin = eval(json.isSuperAdmin.toLowerCase());
            if (json.status == 'success') {
              _this.$router.replace({path: '/home'});
            } else {
              this.updateVerifyCode();
              _this.$alert(resp.data.msg, '登录失败!');
            }
          } else {
            //失败
            _this.$alert(resp.data.msg, '登录失败');
          }
        }, resp=> {
          _this.loading = false;
          _this.$alert('找不到服务器⊙﹏⊙∥!', '失败!');
        });
      },
      updateVerifyCode() {
        this.vcUrl = '/verifyCode?time='+new Date();
      }
    }
  }
</script>
<style>

  #poster {
    background:url("../assets/bei5.png") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }

  html, #app, body{
    margin: 0;
    padding: 0;
  }

  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }

  .el-form-item__content{
    display: flex;
    align-items: center;
  }
</style>
