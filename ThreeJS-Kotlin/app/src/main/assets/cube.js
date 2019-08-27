var scene = new THREE.Scene();
var camera = new THREE.PerspectiveCamera( 75, window.innerWidth/window.innerHeight, 0.1, 1000 );

var renderer = new THREE.WebGLRenderer({ alpha: true }  );
renderer.setSize( window.innerWidth, window.innerHeight );
renderer.setClearColor( 0xffffff, 0 );
document.body.appendChild( renderer.domElement );

var geometry = new THREE.BoxGeometry( 5, 5, 5 );
var cubeMaterials = [
                     new THREE.MeshBasicMaterial({color:0xff0000, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     new THREE.MeshBasicMaterial({color:0x00ff00, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     new THREE.MeshBasicMaterial({color:0x0000ff, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     new THREE.MeshBasicMaterial({color:0xffff00, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     new THREE.MeshBasicMaterial({color:0xff00ff, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     new THREE.MeshBasicMaterial({color:0x00ffff, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     ];
 var cube = new THREE.Mesh( geometry, cubeMaterials );

 var geometry = new THREE.SphereGeometry( 3, 32, 32 );
 var material = new THREE.MeshBasicMaterial( {color: 0xff00ff, wireframe: true} );
 var sphere = new THREE.Mesh( geometry, material );
 scene.add( sphere );

 cube.position.set(4,0,0)
 sphere.position.set(-10,0,0)

 var group = new THREE.Group();
 group.add( cube );
 group.add( sphere );

 scene.add( group );

 camera.position.z = 10;

 var animate = function () {
     requestAnimationFrame( animate );

     cube.rotation.x += 0.01;
     cube.rotation.y += 0.01;

     sphere.rotation.x += 0.01;
     sphere.rotation.y += 0.01;

     renderer.render( scene, camera );
 };

animate();