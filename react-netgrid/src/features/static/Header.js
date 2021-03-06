import React, {useEffect} from "react";

import { NavLink } from "react-router-dom";
import { connect } from "react-redux";
import propTypes from "prop-types";
import { bindActionCreators } from "redux";
import * as loginSliceActions from "../login/loginSlice";


const Header = ({currentUserName, actions, ...props}) => {
  const activeStyle = { color: "#F15B2A" };


  useEffect(() => {
    console.log("I used header effects");
  });

  function checkExp() {
    if (!loginSliceActions.loggedInWithUnexpiredToken()) {
      alert("THE TOKEN IS EXPIRED, I SHOULD LOG YOU OUT.");
    } else {
      let expirationDate = new Date(parseInt(localStorage.getItem("currentUserTokenExpiration")) * 1000 );
      let msLeft = expirationDate - new Date();
      let secondsLeft = msLeft/ 1000;
      alert("There are " + secondsLeft + " more seconds left on this token.");
    }
  }

  function LoginNavItem() {
    if (currentUserName !== null) {
      return (
        <>
          <button onClick={actions.logout}>
            Logout
          </button>
          {" | "}
          Logged in as <span>{currentUserName}</span>
        </>
      )
    }
    return (
      <NavLink to="/login" activeStyle={activeStyle}>
        Login
      </NavLink>
    )
  }

  return (
    <nav>
      <NavLink to="/" activeStyle={activeStyle} exact>
        Home
      </NavLink>
      {" | "}
      <NavLink to="/about" activeStyle={activeStyle}>
        About
      </NavLink>
      {" | "}
      <NavLink to="/users" activeStyle={activeStyle}>
        Users
      </NavLink>
      {" | "}
      <LoginNavItem />
      {" | "}
      <button onClick={checkExp}>Check Expiration</button>
    </nav>
  );
};

Header.propTypes = {
  actions: propTypes.object.isRequired,
  // currentUserName: propTypes.object.isRequired,
};

// Redux will magically call this when our state.users object changes following
// an action being sent to a reducer modifieing state.users
function mapStateToProps(state) {
  return {
    currentUserName: state.login.currentUserName,
  };
}

// this fancy method gets installed into the component's props for you per the export line below
function mapDispatchToProps(dispatch) {
  return {
    actions: {
      setCurrentUsername: bindActionCreators(loginSliceActions.setCurrentUsername, dispatch),
      logout: bindActionCreators(loginSliceActions.logout, dispatch),
    },
  };
}

// export default UsersPage;
export default connect(mapStateToProps, mapDispatchToProps)(Header);
