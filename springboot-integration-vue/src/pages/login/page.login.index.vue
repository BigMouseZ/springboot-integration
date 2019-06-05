<template>
  <div class="login">
    <div class="dt-left">
      <img src="./imgs/login-bg-left.png" alt="" class="bg">
      <p class="title">{{window.document.title}}<span class="version">V {{VERSION}}</span></p>
    </div>
    <div class="dt-right">
      <div class="dt-login-form">
        <form class="dt-form" onsubmit="return false">
          <div class="dt-input">
            <label for="loginName"><i class="icon iconfont icon-user">&#xe60a;</i></label>
            <input type="text" name="loginName" id="loginName" autocomplete="off" placeholder="请输入用户名" v-model="formData.loginName" @focus="focusUserName($event)" @blur="blurUserName($event)" >
            <span class="error" v-text="errorInfo.loginName" v-if="showNameError"></span>
          </div>
          <div class="dt-input">
            <label for="loginPassword"><i class="icon iconfont icon-pwd">&#xe63e;</i></label>
            <input type="password" name="loginPassword" id="loginPassword" autocomplete="off" placeholder="请输入密码" v-model="formData.loginPassword" @focus="focusPassword($event)" @blur="blurPassword($event)">
            <span class="error" v-text="errorInfo.loginPassword" v-if="showPwdError"></span>
          </div>
          <div class="dt-input knowname">
            <el-checkbox v-model="rememberme" @change="changeRemember"> 记住用户名</el-checkbox>
          </div>
          <div class="dt-input">
            <button type="submit" name="button" id="submit" @click="clickSubmit">登录</button>
          </div>
        </form>
      </div>
      <div class="copyright">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;版本：{{VERSION}}&nbsp;&nbsp;
    ©2017 Signalway&nbsp;&nbsp;&nbsp;北京信路威科技股份有限公司&nbsp;&nbsp;&nbsp;版权所有</div>
    </div>
    <!-- 这里是版权信息 -->
  </div>
</template>

<style lang="scss" scoped>
input::-ms-clear, ::-ms-reveal{display: none;}
.login{
  width:100%;
  height:100vh;
  min-width: 1200px;
  position:relative;
  .dt-left{
    width:50%;
    height:100%;
    float:left;
    .bg{
      width:100%;
      height:100%;
      z-index:-1;
    }
    .title{
      color:#4657a5;
      font-weight:bolder;
      font-size:70px;
      text-align:right;
      width:600px;
      height:80px;
      margin:-56vh auto 0;
      position:relative;

    }
    .version{
      position:absolute;
      bottom:-50px;
      right:10px;
      color:#fff;
      font-size:20px;
    }
  }
  .dt-right{
    width:50%;
    height:100%;
    float:right;
    .dt-login-form {
      width: 360px;
      height: 360px;
      margin:calc(50vh - 120px) 0 0 200px;
      .dt-input {
        position: relative;
        margin-bottom:30px;
        height:41px;
        label {
          position: absolute;
          left: 10px;
          bottom: 6px;
          display: inline-block;
          width: 27px;
          height: 26px;
          .icon-user{
            color:#4657a5;
          }
          .icon-pwd{
            color:#4657a5;
          }
        }
        .el-checkbox{
          left:12px;
        }
        .el-checkbox__inner{
          border-radius: 2px;
        }
        input {
          width: 315px;
          height: 40px;
          padding-left:45px;
          line-height: 40px;
          font-size: 14px;
          font-family:微软雅黑;
          border:none;
          border-bottom: 1px solid #b1b9db;
        }
        .focus{
          border-bottom:1px solid #162b96;
        }
        span{
          display:block;
          position:absolute;
          top:10px;
          right:0;
          color:red;
          font-size:12px;
        }
        button {
          width: 360px;
          height: 40px;
          border: none;
          background-color: #162b96;
          color: #fff;
          text-align: center;
          font-size: 16px;
          font-weight: 600;
          letter-spacing: 8px;
          cursor: pointer;
          outline: none;
          &:hover {
            background-color: #1b35b7;
          }
        }
      }
      .knowname{
        margin-bottom:20px;
        height:20px;
      }
    }
    .copyright{
        width:100%;
        text-align:center;
        font-size:14px;
        color:#4657a5;
        position:absolute;
        bottom:10px;
        left:0;
      }
  }
}
</style>
