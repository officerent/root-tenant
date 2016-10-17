(function(){
  'use strict';

  // bootstrap components
  $( '[rel="tooltip"]' ).tooltip({
    container: 'body'
  });

  // Initialize Switchery
  var jsSwitch = document.querySelectorAll('.js-switch-setup');
  [].forEach.call( jsSwitch, function( el ){
    var data = el.dataset,
    options = {
      color           : ( data.color ) ? data.color : '#5D9CEC',
      secondaryColor  : ( data.secondaryColor ) ? data.secondaryColor : '#E6E9ED',
      className       : ( data.className ) ? data.className : 'switchery',
      disabled        : ( data.disabled ) ? data.disabled : false,
      disabledOpacity : ( data.disabledOpacity ) ? data.disabledOpacity : 0.5,
      speed           : ( data.speed ) ? data.speed : '0.3s'
    };

    new Switchery( el, options );
  });
  // chage switchery
  function switcheryOnChange(el) {
    el = (typeof(el) === 'string') ? document.querySelector(el) : el;
    if (typeof Event === 'function' || !document.fireEvent) {
      var event = document.createEvent('HTMLEvents');
      event.initEvent('change', true, true);
      el.dispatchEvent(event);
    } else {
      el.fireEvent('onchange');
    }
  }


  // LAYOUT SETUPS
  // =====================================
  // default switchery
  document.querySelector('#layout-fs').checked = window.wl.options.fullscreen;
  document.querySelector('#layout-box').checked = window.wl.options.box;
  switcheryOnChange('#layout-fs');
  switcheryOnChange('#layout-box');

  var randomBg = document.querySelector('#layout-bg'),
  bgClass = [ 'red', 'orange', 'yellow', 'green', 'teal', 'cyan', 'blue', 'violet', 'dark', 'light'];
  randomBg.addEventListener( 'click', function(e){
    e.preventDefault();
    var rand = bgClass[Math.floor(Math.random() * bgClass.length)],
    exist = (document.body.dataset.bg);

    if (exist) {
      classie.remove(document.body, 'bg-grd-' + exist );
    }
    classie.add(document.body, 'bg-grd-' + rand );
    document.body.dataset.bg = rand;

    $.cookie( 'template_bg', rand );
  });

  // Listen Events
  $( document ).on( 'change', '#layout-fs', function(){
    var checked = $( this ).is(':checked');

    if ( checked ) {
      window.wl.fullscreen();
    } else{
      window.wl.exitFullscreen();
    }
  })
  .on( screenfull.raw.fullscreenchange, function () {
    var isFs = screenfull.isFullscreen,
    el = document.querySelector('#layout-fs');

    $( el ).prop( 'checked', isFs );
    switcheryOnChange( el );
  })
  .on( 'change', '#layout-box', function(){
    var checked = $( this ).is(':checked');

    if ( checked ) {
      window.wl.setBox();
    } else{
      window.wl.setFluid();
    }
  });


  // HEADER SETUPS
  // =====================================
  // default switchery
  document.querySelector('#header-rtl').checked = window.wh.options.rtlMode;
  document.querySelector('#header-fixed').checked = window.wh.options.fixed;
  switcheryOnChange('#header-rtl');
  switcheryOnChange('#header-fixed');

  var headerSkins = document.querySelectorAll('[data-setup-header="toggleSkin"]');

  [].forEach.call(headerSkins, function(el){
    // default
    var skin = el.dataset.skin;
    if (window.wh.options.skin === skin) {
      classie.add(el.parentNode, 'active');
    }
    el.addEventListener( 'click', function(e){
      e.preventDefault();
      var skin = el.dataset.skin,
      ul = WrapkitUtils.closest(this, 'ul'),
      act = ul.querySelector('li.active');

      window.wh.setSkin( skin );

      // set active class
      classie.remove(act, 'active');
      classie.add(this.parentNode, 'active');
    });
  });

  // Listen Events
  $( document ).on( 'change', '#header-rtl', function(){
    var checked = $( this ).is(':checked');

    window.wh.rtl(checked);
  })
  .on( 'change', '#header-fixed', function(){
    var checked = $( this ).is(':checked');

    window.wh.fixed(checked);
  });




  // SIDEBAR SETUPS
  // =====================================
  // default switchery
  document.querySelector('#sidebar-align').checked = window.sidebar.options.align === 'right';
  document.querySelector('#sidebar-rtl').checked = window.sidebar.options.rtlMode;
  document.querySelector('#sidebar-fixed').checked = window.sidebar.options.fixed;
  document.querySelector('#sidebar-resizable').checked = window.sidebar.options.resizable;
  switcheryOnChange('#sidebar-align');
  switcheryOnChange('#sidebar-rtl');
  switcheryOnChange('#sidebar-fixed');
  switcheryOnChange('#sidebar-resizable');

  var toggleVisible = document.querySelectorAll('[data-setup-sidebar="toggleVisible"]'),
  sidebarSkins = document.querySelectorAll('[data-setup-sidebar="toggleSkin"]'),
  toggleVariant  = document.querySelectorAll('[data-setup-sidebar="toggleVariant"]'),
  toggleMode  = document.querySelectorAll('[data-setup-sidebar="toggleMode"]'),
  toggleSize  = document.querySelectorAll('[data-setup-sidebar="toggleSize"]'),
  toggleContext  = document.querySelectorAll('[data-setup-sidebar="toggleContext"]'),
  toggleLoader  = document.querySelectorAll('[data-setup-sidebar="toggleLoader"]');

  [].forEach.call(toggleVisible, function(el){
    el.addEventListener( 'click', function(e){
      e.preventDefault();

      if (window.sidebar.options.visible){
        window.sidebar.hide();
      }
      else{
        window.sidebar.show();
      }
    });
  });
  [].forEach.call(sidebarSkins, function(el){
    // default selected el
    var skin = el.dataset.skin;
    if (window.sidebar.options.skin === skin) {
      classie.add(el.parentNode, 'active');
    }
    el.addEventListener( 'click', function(e){
      e.preventDefault();
      var skin = el.dataset.skin,
      ul = WrapkitUtils.closest(this, 'ul'),
      act = ul.querySelector('li.active');

      window.sidebar.setSkin( skin );

      // set active class
      classie.remove(act, 'active');
      classie.add(this.parentNode, 'active');
    });
  });
  [].forEach.call(toggleVariant, function(el){
    // default selected el
    var variant = el.dataset.variant;
    if (window.sidebar.options.variant === variant) {
      el.children[0].checked = true;
      classie.add(el, 'active');
    }
    el.addEventListener('click', function(e){
      e.preventDefault();

      var variant = this.dataset.variant;
      window.sidebar.setVariant(variant);
    });
  });
  [].forEach.call(toggleContext, function(el){
    // default selected el
    var context = el.dataset.context;
    if (window.sidebar.options.context === context) {
      classie.add(el.parentNode, 'active');
    }
    el.addEventListener('click', function(e){
      e.preventDefault();

      var context = this.dataset.context,
      ul = WrapkitUtils.closest(this, 'ul'),
      act = ul.querySelector('li.active');

      window.sidebar.setContext(context);

      // set active class
      classie.remove(act, 'active');
      classie.add(this.parentNode, 'active');
    });
  });
  [].forEach.call(toggleMode, function(el){
    // default selected el
    var mode = el.dataset.mode;
    if (window.sidebar.options.mode === mode) {
      el.children[0].checked = true;
      classie.add(el, 'active');
    }
    el.addEventListener('click', function(e){
      e.preventDefault();

      var mode = this.dataset.mode;
      window.sidebar.setMode(mode);
    });
  });
  [].forEach.call(toggleSize, function(el){
    // default selected el
    var size = el.dataset.size;
    if (window.sidebar.options.size === size) {
      el.children[0].checked = true;
      classie.add(el, 'active');
    }
    el.addEventListener('click', function(e){
      e.preventDefault();

      var size = this.dataset.size;
      window.sidebar.size(size);
    });
  });
  [].forEach.call(toggleLoader, function(el){
    el.addEventListener( 'click', function(e){
      e.preventDefault();

      window.sidebar.showLoader(2);

      setTimeout( function(){
        window.sidebar.hideLoader(2);
      }, 3000);
    });
  });

  // Listen Events
  $( document ).on( 'change', '#sidebar-align', function(e){
    e.preventDefault();
    var align = ($(this).is(':checked')) ? 'right' : 'left';

    window.sidebar.align(align);
  })
  .on( 'change', '#sidebar-rtl', function(e){
    e.preventDefault();
    var checked = $(this).is(':checked');

    window.sidebar.rtl(checked);
  })
  .on( 'change', '#sidebar-fixed', function(e){
    e.preventDefault();
    var checked = $(this).is(':checked');

    window.sidebar.fixed(checked);
  })
  .on( 'change', '#sidebar-resizable', function(e){
    e.preventDefault();
    var checked = $(this).is(':checked');

    window.sidebar.resizable(checked);
  });


  // CONTENT SETUPS
  // =====================================
  // default switchery
  document.querySelector('#content-rtl').checked = window.wc.options.rtlMode;
  switcheryOnChange('#content-rtl');

  // Listen Events
  $( document ).on( 'change', '#content-rtl', function(e){
    e.preventDefault();
    var checked = $(this).is(':checked');

    window.wc.rtl(checked);
  });

  // FOOTER SETUPS
  // =====================================
  // default switchery
  document.querySelector('#footer-rtl').checked = window.wf.options.rtlMode;
  switcheryOnChange('#footer-rtl');

  var footerSkins = document.querySelectorAll('[data-setup-footer="toggleSkin"]');

  [].forEach.call(footerSkins, function(el){
    // default
    var skin = el.dataset.skin;
    if (window.wf.options.skin === skin) {
      classie.add(el.parentNode, 'active');
    }
    el.addEventListener( 'click', function(e){
      e.preventDefault();
      var skin = el.dataset.skin,
      ul = WrapkitUtils.closest(this, 'ul'),
      act = ul.querySelector('li.active');

      window.wf.setSkin( skin );

      // set active class
      classie.remove(act, 'active');
      classie.add(this.parentNode, 'active');
    });
  });
  // Listen Events
  $( document ).on( 'change', '#footer-rtl', function(e){
    e.preventDefault();
    var checked = $(this).is(':checked');

    window.wf.rtl(checked);
  });


  // SAMPLE SKINS
  // =====================================
  var sampleSkins = document.querySelectorAll('[data-toggle="sampleSkins"]');

  [].forEach.call(sampleSkins, function(el){
    // default
    var skins = el.dataset.skin.split(" "),
    context = el.dataset.context;
    if (window.wh.options.skin === skins[0] && window.sidebar.options.skin === skins[1] && window.sidebar.options.context === context) {
      classie.add(el.parentNode, 'active');
    }
    el.addEventListener( 'click', function(e){
      e.preventDefault();

      var skins = this.dataset.skin.split(" "),
      hs = skins[0],
      ss = skins[1],
      context = this.dataset.context;

      var ul = WrapkitUtils.closest(this, 'ul'),
      act = ul.querySelector('li.active');

      window.wh.setSkin(hs);
      window.sidebar.setSkin(ss);
      window.sidebar.setContext(context);

      // set active class
      if (act) {
        classie.remove(act, 'active');
      }
      classie.add(this.parentNode, 'active');
    });
  });



  // Listen Event
  // show/hide custom skin options
  $( document ).on( 'change', '#skins-custom', function(){
    var custom = $( this ).is(':checked');

    if ( custom ) {
      $( '#component-skin-group' ).collapse('show');
    } else{
      $( '#component-skin-group' ).collapse('hide');
    }
  });



  // Save data options to cookie
  // Save Setups
  $( document ).on( 'click', '#savesSetups', function(e){
    e.preventDefault();
    $.cookie.json = true;

    $.cookie( 'template_setups', true );
    $.cookie( 'template_setups_layout', window.wl.options );
    $.cookie( 'template_setups_header', window.wh.options );
    $.cookie( 'template_setups_sidebar', window.sidebar.options );
    $.cookie( 'template_setups_content', window.wc.options );
    $.cookie( 'template_setups_footer', window.wf.options );

    // $.cookie( 'template_bg', bgStyle );
    // $.cookie( 'template_sample_skin', $( '.skins-sample > li.active > a' ).data( 'skin' ) );

    $( '#templateSetup' ).modal('hide');
  })
  // Restore Setups
  .on( 'click', '#restoreSetups', function(e){
    e.preventDefault();

    $.removeCookie( 'template_setups' );
    $.removeCookie( 'template_setups_layout' );
    $.removeCookie( 'template_setups_header' );
    $.removeCookie( 'template_setups_content' );
    $.removeCookie( 'template_setups_sidebar' );
    $.removeCookie( 'template_setups_footer' );
    $.removeCookie( 'template_bg' );

    location.reload();
  });

})(window);