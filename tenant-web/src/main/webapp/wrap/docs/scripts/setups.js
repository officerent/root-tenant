(function(){
  'use strict';

  // DEFINE VARIABLES
  // =====================================
  var WrapkitLayout = window.WrapkitLayout,
  WrapkitHeader  = window.WrapkitHeader,
  WrapkitSidebar = window.WrapkitSidebar,
  WrapkitContent = window.WrapkitContent,
  WrapkitUtils   = window.WrapkitUtils,
  classie  = window.classie;

  // initialize and make it global.
  window.wl = new WrapkitLayout( '.wrapkit-wrapper' );
  window.wh = new WrapkitHeader( '.header', {
    fixed: true
  });
  window.sidebar = new WrapkitSidebar( '.sidebar', {
    fixed: true,
    skin: 'dark',
    context: 'teal',
    resizable: false
  });
  window.wc = new WrapkitContent( '.content-wrapper' );

  // initialize panel
  var WrapkitPanel = window.WrapkitPanel,
  panels = document.querySelectorAll('.content .panel');

  [].forEach.call(panels, function(panel){
    new WrapkitPanel( panel, panel.dataset );
  });


  // Layout settings
  window.addEventListener( 'resize', WrapkitUtils.debounce(function(){
    if( WrapkitUtils.viewport().width > 767 && !window.sidebar.options.visible ){
      window.sidebar.show();
    }
  }, 250));
  // change theme
  if ($.cookie( 'doc_theme' ) === 'dark') {
    window.wh.setSkin('inverse');
    classie.add( window.wc.elem, 'content-dark');
  } else{
    window.wh.setSkin('default');
    classie.remove( window.wc.elem, 'content-dark');
  }
  document.querySelector('#light-theme').addEventListener( 'click', function(e){
    e.preventDefault();

    window.wh.setSkin('default');
    classie.remove( window.wc.elem, 'content-dark');
    $.cookie( 'doc_theme', 'light' );
  });
  document.querySelector('#dark-theme').addEventListener( 'click', function(e){
    e.preventDefault();

    window.wh.setSkin('inverse');
    classie.add( window.wc.elem, 'content-dark');
    $.cookie( 'doc_theme', 'dark' );
  });



  // ADD EVENTS FOR INTERACTIONS
  // =====================================
  // Sidebar
  var toggleVisible  = document.querySelectorAll('[data-sidebar="toggleVisible"]');
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



  // LISTEN WHEN USER FIRE AN EVENT
  // =====================================
  window.sidebar.on( '_expand', function( e, node ){
    var $target = $(node.querySelector('.nav-child'));
    $.Velocity.RunSequence([
      { e: $target, p: 'transition.fadeIn', o: { duration: 250 } },
      { e: $target.children('li'), p: 'transition.slideUpIn', o: { stagger: 35, sequenceQueue: false } }
      ]);
  })
  .on( '_collapse', function( e, node ){
    var $target = $(node.querySelectorAll('.nav-child'));
    $target.velocity('transition.fadeOut', { duration: 250 });
  })
  .on( 'align', function( e, align ){
    if (align === 'left') {
      e.$elem.velocity('transition.slideLeftIn', { duration: 250 });
    } else{
      e.$elem.velocity('transition.slideRightIn', { duration: 250 });
    }
  })
  .on( 'fixer', function(){
    var $sidebarNav = $('.nav-wrapper');

    if ($sidebarNav.parent().hasClass('slimScrollDiv')) {
      setTimeout(function(){
        var height = $sidebarNav.css('height');
        $sidebarNav.parent().css('height', height);
      }, 250);
    }
  })
  .on( 'fixed', function(e, fixed){
    var el = $('.nav-wrapper');

    if (fixed) {
      WrapkitUtils.initSlimScroll(el);
    } else{
      WrapkitUtils.destroySlimScroll(el);
    }
  });




  // load content by section to easy maintenance
  $('[data-tmpl]').each(function(index, el) {
    var url = el.dataset.tmpl;

    $(el).load(url, function(){
      Prism.highlightAll();
    });
  });

})(window);